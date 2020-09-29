package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication/*(scanBasePackages = "com.atguigu.springcloud")*/
@EnableEurekaClient
// 开启 FeignClient 功能，basePackages 指定 FeignClient 的扫描位置
@EnableFeignClients(basePackages= {"com.atguigu.springcloud"})
// 指定扫描包，若当前主程序类所在的包与 FeignClient 所在的包不同，则需要将当前包与 FeignClient 所在的包都包含进来
@ComponentScan("com.atguigu.springcloud")
public class DeptConsumer80_Feign_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer80_Feign_App.class, args);
	}
}
