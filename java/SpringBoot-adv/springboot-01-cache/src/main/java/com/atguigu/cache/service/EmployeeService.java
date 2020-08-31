package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp"/*, cacheManager = "employeeCacheManager"*/)    // 抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中获取，不用调用方法。
     * <p>
     * CacheManager 管理多个 Cache 组件，对缓存
     * 几个属性：
     * cacheNames/value：指定缓存的名字。将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存。
     * key：缓存数据使用的 key，可以用它来指定。默认是使用方法参数的值  1-方法的返回值
     * 可编写 SpEL：#id 参数id的值     #a0 #p0 #root.args[0]
     * getEmp[2] ---> key = "#root.methodName+'['+#id+']'"
     * keyGenerator：key 的生成器。可以自己指定 key 的生成器的组件 id
     * key/keyGenerator：二选一使用
     * cacheManager：指定缓存管理器，或者使用 cacheResolver 指定获取解析器
     * cacheManager/cacheResolver：二选一使用
     * condition：指定符合条件的情况下才缓存
     * condition = "#id>0"
     * condition = "#a0>1"：第一个参数的值>1的时候才进行缓存
     * condition = "#a0>1 and #root.methodName eq 'aaa'"
     * unless：否定缓存，与 condition 相反，当 unless 指定的条件为 true，方法的返回值就不会被缓存
     * unless = "#result == null"
     * unless = "#a0==2"
     * sync：是否使用异步模式
     * 异步模式下，不支持 unless 属性。
     * <p>
     * 原理：
     * 1、自动配置类：CacheAutoConfiguration
     * 2、缓存的配置类：
     * 0 = "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     * 1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     * 2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     * 3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     * 4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     * 5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     * 6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     * 7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     * 8 = "org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration"
     * 9 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"【默认】
     * 10 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     * 3、哪个配置类默认生效？ SimpleCacheConfiguration
     * 4、给容器中注册了一个 CacheManager：ConcurrentMapCacheManager
     * 5、可以获取和创建 ConcurrentMapCache 类型的缓存组件，它的作用是将数据保存在 ConcurrentMap 中
     * <p>
     * 运行流程：
     *
     * @param id
     * @return
     * @Cacheable
     * 1、方法运行前，先去查询 Cache（缓存组件），按照 cacheNames 指定的名字获取
     * CacheManager 先获取相应的缓存，第一次获取缓存时，如果没有 Cache 组件，会自动创建。
     * 2、去 Cache 中查找缓存的内容，使用一个 key，默认就是方法的参数。
     * key 是按照某种策略生成的，默认是使用 keyGenerator 生成的，默认使用 SimpleKeyGenerator 生成 key。
     * SimpleKeyGenerator 生成 key 的默认策略：
     * 如果没有参数：key =  new SimpleKey();
     * 如果有一个参数：key = 参数值
     * 如果有多个参数：key = new SimpleKey(params);
     * 3、若没有查到缓存，就调用目标方法
     * 4、将目标方法返回的结果放进缓存
     * @Cacheable 标注的方法执行之前先检查缓存中有没有这个数据，默认按照参数的值作为 key 去查询缓存，
     * 如果没有，就运行方法并将结果放入缓存，以后再来调用就可以直接使用缓存中的数据。
     * <p>
     * 核心：
     * 1）、使用 CacheManager【ConcurrentMapCacheManager】按照名字得到 Cache【ConcurrentMapCache】组件
     * 2）、key 使用 keyGenerator 生成的，默认是 SimpleKeyGenerator
     */
    @Cacheable(cacheNames = {"emp"}, keyGenerator = "myKeyGenerator"/*, condition = "#a0>1", unless = "#a0==2"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @param employee
     * @return
     * @CachePut： 先调用方法，后更新缓存数据
     * 修改了数据库的某个数据，同时更新缓存。
     * 注意：@CachePut 要和 @Cacheable 的 key 一致
     * 运行时机：
     * 1、先调用方法
     * 2、将目标方法的结果缓存起来
     * <p>
     * 测试：
     * 1、查询1号员工，查到的结果会放到缓存中
     * 2、以后查询还是之前的结果
     * 3、更新1号员工：lastName: zhangsan; gender: 0
     * 将方法返回值也放进缓存了
     * key：传入的 employee 对象    值：返回的 employee 对象
     * 4、查询1号员工，
     * 应该是更新后的员工
     * key = "#employee.id"：使用传入的参数的员工ID
     * key = "#result.id"：使用返回后的对象中的id
     * 注意，@Cacheable 的 key 不能使用 #ressult 取出结果
     * 为什么没有更新之前的结果？【1号员工没有在缓存中更新，因为 key 不同】
     */
    @CachePut(/*value = "emp",*/ key = "#result.id")
    public Employee updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除 key：指定要清除的数据
     * allEntries = true：指定清除这个缓存中的所有数据
     * beforeInvocation = false：缓存的清除是否在方法之前执行
     * 默认代表缓存清除操作是在方法运行之后执行，如果出现异常，缓存就不会清除
     * beforeInvocation = true：
     * 代表缓存清除操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp: " + id);
        employeeMapper.deleteEmpById(id);
        int i = 10 / 0;
    }

    /**
     * @Caching 定义复杂的缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/ key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp",*/ key = "#result.id"),
                    @CachePut(/*value = "emp",*/ key = "#result.email")
            }


    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }

}
