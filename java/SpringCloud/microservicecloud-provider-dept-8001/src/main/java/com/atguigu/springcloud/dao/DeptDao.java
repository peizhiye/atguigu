package com.atguigu.springcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.atguigu.springcloud.entities.Dept;

@Mapper	// 注意，这里的 @Mapper 注解不能省略，或者在启动类上添加 @MapperScan(value = "com.atguigu.springcloud.dao") 指定包扫描路径
public interface DeptDao
{
	public boolean addDept(Dept dept);

	public Dept findById(Long id);

	public List<Dept> findAll();
}
