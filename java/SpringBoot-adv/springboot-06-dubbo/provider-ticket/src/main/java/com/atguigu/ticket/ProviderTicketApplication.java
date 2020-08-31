package com.atguigu.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 1、将服务提供者注册到注册中心
 *      1）、引入 dubbo 和 zkclient 相关依赖
 *      2）、配置 dubbo 的扫描包和注册中心地址
 *      3）、使用 dubbo 的 @Service 发布服务
 */
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }
}
