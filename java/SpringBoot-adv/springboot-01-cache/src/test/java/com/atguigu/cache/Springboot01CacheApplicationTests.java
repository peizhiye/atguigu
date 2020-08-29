package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;    // 操作 k-v 都是字符串的
    @Autowired
    RedisTemplate redisTemplate;    // 操作 k-v 都是对象的
    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void test02() {
        Employee empById = employeeMapper.getEmpById(1);
        // 默认如果保存对象，使用 JDK 序列化机制，序列化后的数据保存到 redis 中
//        redisTemplate.opsForValue().set("emp-01", empById);

        // 1、将数据以 JSON 的方式保存
        // （1）自己将对象转为 JSON
        // （2）redisTemplate 默认的序列化规则是 JdkSerializationRedisSerializer；
        //      可以通过自定义 RedisTemplate，设置相应的序列化器（RedisSerializer），来改变默认的序列化规则
        empRedisTemplate.opsForValue().set("emp-01", empById);
    }

    /**
     * Redis 常见的五大数据类型
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue()    String（字符串）
     * stringRedisTemplate.opsForList()     List（列表）
     * stringRedisTemplate.opsForSet()      Set（集合）
     * stringRedisTemplate.opsForHash()     Hash（散列）
     * stringRedisTemplate.opsForZSet()     ZSet（有序集合）
     *
     */
    @Test
    public void test01() {
        // 给 redis 中保存数据
        stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");

    }

    @Test
    public void contextLoads() {
        Employee empById = this.employeeMapper.getEmpById(1);
        System.out.println(empById);
    }
}

