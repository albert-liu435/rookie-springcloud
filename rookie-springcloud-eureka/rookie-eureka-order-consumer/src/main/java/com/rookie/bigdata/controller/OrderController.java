package com.rookie.bigdata.controller;

import com.rookie.bigdata.entities.CommonResult;
import com.rookie.bigdata.entities.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * @Classname PaymentController
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:45
 * @Version 1.0
 */
@RestController
public class OrderController {

    protected final Logger log = LoggerFactory.getLogger(OrderController.class);

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;


    // @GetMapping("/consumer/payment/create")
    @GetMapping(value = "/consumer/payment/create", produces = "application/json; charset=utf-8")
    public CommonResult<Payment> create( @RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    //@GetMapping("/consumer/payment/get/{id}")
    @GetMapping(value = "/consumer/payment/get/{id}", produces = "application/json; charset=utf-8")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }


}
