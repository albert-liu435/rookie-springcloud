package com.rookie.bigdata.controller;


import com.rookie.bigdata.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Classname PaymentController
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:45
 * @Version 1.0
 */
@RestController
public class PaymentController {

    protected final Logger log = LoggerFactory.getLogger(PaymentController.class);


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentInfo_OK(id);
        log.info("*****result: "+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*****result : "+result);
        return result;
    }

//    //====服务熔断
//    @GetMapping("/payment/circuit/{id}")
//    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
//    {
//        String result = paymentService.paymentCircuitBreaker(id);
//        log.info("****result: "+result);
//        return result;
//    }


}
