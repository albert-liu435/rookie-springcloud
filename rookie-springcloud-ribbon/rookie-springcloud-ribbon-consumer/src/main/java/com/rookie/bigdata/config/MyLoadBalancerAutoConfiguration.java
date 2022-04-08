package com.rookie.bigdata.config;

import com.rookie.bigdata.annotation.MyLoadBalanced;
import com.rookie.bigdata.interceptor.MyHttpInterceptor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Classname MyLoadBalancerAutoConfiguration
 * @Description TODO
 * @Author rookie
 * @Date 2021/10/19 10:04
 * @Version 1.0
 */
@Configuration
public class MyLoadBalancerAutoConfiguration {


    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> myTemplates = Collections.emptyList();


    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer() {
        return new SmartInitializingSingleton() {

            public void afterSingletonsInstantiated() {
                for (RestTemplate tpl : myTemplates) {
                    MyHttpInterceptor mi = new MyHttpInterceptor();
                    List list = new ArrayList(tpl.getInterceptors());
                    list.add(mi);
                    tpl.setInterceptors(list);
                }
            }
        };
    }


}
