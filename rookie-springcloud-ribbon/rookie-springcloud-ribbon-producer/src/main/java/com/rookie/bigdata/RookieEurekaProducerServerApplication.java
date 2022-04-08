package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Classname RookieEurekaProducerServerApplication
 * @Description TODO
 * @Author rookie
 * @Date 2021/10/15 15:25
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RookieEurekaProducerServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(RookieEurekaProducerServerApplication.class, args);
    }

}
