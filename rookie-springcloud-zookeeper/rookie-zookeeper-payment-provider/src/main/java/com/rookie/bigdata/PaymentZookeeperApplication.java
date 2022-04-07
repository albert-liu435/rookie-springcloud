package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname PaymentApplication
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 13:59
 * @Version 1.0
 */
@SpringBootApplication
//该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@EnableDiscoveryClient
public class PaymentZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentZookeeperApplication.class, args);

    }
}
