package com.rookie.bigdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname OrderConfig
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 16:46
 * @Version 1.0
 */
@Configuration
public class OrderConfig {


    @Bean

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
