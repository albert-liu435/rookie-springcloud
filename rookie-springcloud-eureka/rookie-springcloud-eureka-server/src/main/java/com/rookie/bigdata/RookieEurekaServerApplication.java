package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Classname RookieEurekaServerApplication
 * @Description java -jar -Dspring.profiles.active=dev springboot-demo.jar
 * java -jar -Dspring.profiles.active=8761 rookie-springcloud-eureka-server-1.0-SNAPSHOT.jar
 * @Author rookie
 * @Date 2021/10/15 14:09
 * @Version 1.0
 */
@EnableEurekaServer
@SpringBootApplication
public class RookieEurekaServerApplication {


    public static void main(String[] args) {

        SpringApplication.run(RookieEurekaServerApplication.class, args);

    }
}
