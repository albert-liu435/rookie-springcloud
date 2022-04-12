package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Classname RookieEurekaServerApplication
 * @Description java -jar -Dspring.profiles.active=dev rookie-eureka-server-1.0.0.jar
 * java -jar -Dspring.profiles.active=8761 rookie-springcloud-eureka-server-1.0-SNAPSHOT.jar
 * @Author rookie
 * @Date 2021/10/15 14:09
 * @Version 1.0
 */
//@EnableEurekaServer
@SpringBootApplication
@EnableConfigServer
public class RookieConfigCenterApplication {


    public static void main(String[] args) {

        SpringApplication.run(RookieConfigCenterApplication.class, args);

    }
}
