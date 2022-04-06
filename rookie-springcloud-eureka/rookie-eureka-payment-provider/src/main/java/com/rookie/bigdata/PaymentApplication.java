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
@EnableEurekaClient
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);

    }
}
