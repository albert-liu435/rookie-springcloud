package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author rookie
 * @Date 2021/10/15 14:09
 * @Version 1.0
 */

@SpringBootApplication
@EnableEurekaClient
public class RookieGateWayApplication {


    public static void main(String[] args) {

        SpringApplication.run(RookieGateWayApplication.class, args);

    }
}
