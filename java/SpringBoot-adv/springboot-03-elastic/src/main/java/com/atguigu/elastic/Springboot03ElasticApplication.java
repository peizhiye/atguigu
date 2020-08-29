package com.atguigu.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * SpringBoot 默认支持两种技术来和 ES 交互：
 * 1、Jest（默认不生效）
 *      需要导入 jest 的工具包：io.searchbox.client.JestClient
 * 2、SpringData ElasticSearch【ES 版本可能不合适】
 *      版本适合说明：https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#preface.versions
 *      如果版本不适配：
 *          1）、升级 SpringBoot 版本
 *          2）、安装对应版本的 ES（2.4.6 版本）
 *      1）、Client 节点信息：clusterNodes、clusterName
 *      2）、ElasticsearchTemplate 操作 ES
 *      3）、缩写一个 ElasticsearchRepository 的子接口来操作 ES
 *
 *    两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *    1）、编写一个 ElasticsearchRepository 的子接口
 *    2）、使用 ElasticsearchTemplate
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticApplication.class, args);
    }
}
