package com.rookie.bigdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ProducerController
 * @Description TODO
 * @Author rookie
 * @Date 2021/10/15 15:28
 * @Version 1.0
 */

@RestController
public class ProducerController {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping("/producer/hello")
    public ResponseEntity<String> hello() {
        logger.info("request: {}", "hello");




        return ResponseEntity.ok("hello eureka port:"+ serverProperties.getPort());
    }

}
