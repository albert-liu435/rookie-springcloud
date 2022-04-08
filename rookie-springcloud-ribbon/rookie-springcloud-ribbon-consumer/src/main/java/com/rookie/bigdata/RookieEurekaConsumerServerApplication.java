package com.rookie.bigdata;

import com.rookie.bigdata.annotation.MyLoadBalanced;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @Classname RookieEurekaProducerServerApplication
 * @Description EnableDiscoveryClient 可以省略  http://localhost:10001/consumer/hello
 * @Author rookie
 * @Date 2021/10/15 15:25
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RookieEurekaConsumerServerApplication {


//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }


    //自定义拦截器注解
    @Bean
    @MyLoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(RookieEurekaConsumerServerApplication.class, args);
    }

}
