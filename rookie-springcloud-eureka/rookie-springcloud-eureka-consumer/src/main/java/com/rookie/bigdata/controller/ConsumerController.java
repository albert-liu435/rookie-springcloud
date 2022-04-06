package com.rookie.bigdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname ConsumerController
 * @Description TODO
 * @Author rookie
 * @Date 2021/10/15 15:44
 * @Version 1.0
 */

@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/hello")
    public ResponseEntity<String> getId() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://eureka-producer/producer/hello", String.class);
        String hello = result.getBody();
        logger.info("request: {}", hello);
        return ResponseEntity.ok(hello);
    }


}
