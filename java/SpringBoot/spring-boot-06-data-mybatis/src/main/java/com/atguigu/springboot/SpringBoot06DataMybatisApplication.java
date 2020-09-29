package com.atguigu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @MapperScan 注解，开启 Mapper 扫描。
 * 类似于 mybatis 与 Spring 整合的配置文件中的
 * <mybatis-spring:scan base-package="com.atguigu.springboot.mapper"/> 配置
 * 开启了 @MapperScan 之后，在 Mapper 接口上就可以不用标注 @Mapper 注解
 */
@MapperScan(value = "com.atguigu.springboot.mapper")
@SpringBootApplication
public class SpringBoot06DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot06DataMybatisApplication.class, args);
	}
}
