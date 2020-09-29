package com.atguigu.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


/**
 * 1、导入依赖；
 * 		1）、导入dubbo-starter
 * 		2）、导入dubbo的其他依赖
 * @author lfy
 *
 * SpringBoot与dubbo整合的三种方式：
 * 1、导入dubbo-starter，在application.properties配置属性，使用@Service【暴露服务】使用@Reference【引用服务】
 * 		注意：可以使用2种方式开启注解功能：
 * 		1）、在配置类中使用 @EnableDubbo 注解
 * 		2）、在 application.properties 中配置 dubbo.scan.base-packages 属性，指定要扫描的包
 * 2、保留 dubbo xml 配置文件;
 * 		导入dubbo-starter，使用@ImportResource导入dubbo的配置文件即可
 * 3、使用注解API的方式：
 * 		将每一个组件手动创建到容器中,让dubbo来扫描其他的组件
 */
//@EnableDubbo //开启基于注解的dubbo功能
//@ImportResource(locations="classpath:provider.xml")
//@EnableDubbo(scanBasePackages="com.atguigu.gmall")
//@DubboComponentScan()
@DubboComponentScan(basePackages="com.atguigu.gmall")	// 若不指定 basePackages，则扫描全部
@EnableHystrix //开启服务容错
@SpringBootApplication
public class BootUserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceProviderApplication.class, args);
	}
}
