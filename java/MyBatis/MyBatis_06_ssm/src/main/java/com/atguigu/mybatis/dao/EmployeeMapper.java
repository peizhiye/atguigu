package com.atguigu.mybatis.dao;


import java.util.List;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getEmps();
	

}
