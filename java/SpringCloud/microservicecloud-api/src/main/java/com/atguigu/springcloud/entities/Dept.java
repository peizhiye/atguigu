package com.atguigu.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@NoArgsConstructor	// 空参构造函数
//@AllArgsConstructor	// 全参构造函数
@Data				// setter/getter 方法
@Accessors(chain=true)	// 链式调用
public class Dept implements Serializable// entity --orm--- db_table 类表关系映射，必须序列化（实测未序列化也能用）
{
	private Long 	deptno; // 主键
	private String 	dname; // 部门名称
	private String 	db_source;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库
	
	public Dept(String dname)
	{
		super();
		this.dname = dname;
	}

}
