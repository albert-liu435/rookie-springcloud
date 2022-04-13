package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Classname PaymentApplication
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 13:59
 * @Version 1.0
 */
@SpringBootApplication
//该注解应该可以去掉
@EnableEurekaClient
public class OrderBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBusApplication.class, args);

    }
}
