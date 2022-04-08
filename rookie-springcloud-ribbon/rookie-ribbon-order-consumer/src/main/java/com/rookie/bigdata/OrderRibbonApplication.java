package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

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
//@RibbonClient(name = "rookie-eureka-payment")
public class OrderRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderRibbonApplication.class, args);

    }
}
