package com.atguigu.cache.service;


import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

//@CacheConfig(cacheNames = "dept", cacheManager = "deptCacheManager")
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

    /**
     * 缓存的数据能存入 redis
     * 第二次从缓存中查询就不能反序列化回来
     * 存的是 dept 的 JSON 数据，但 CacheManager 默认使用 RedisTemplate<Object, Employee> 来操作 redis
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "dept", cacheManager = "deptCacheManager")
//    public Department getDeptById(Integer id) {
//        System.out.println("查询部门" + id);
//        Department department = departmentMapper.getDeptById(id);
//        return department;
//    }

    /**
     * 通过编码的方式操作缓存（而不是通过注解来操作）
     * 使用缓存管理器得到缓存，进行 API 调用
     * @param id
     * @return
     */
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);

        // 获取某个缓存
        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1", department);

        return department;
    }
}
