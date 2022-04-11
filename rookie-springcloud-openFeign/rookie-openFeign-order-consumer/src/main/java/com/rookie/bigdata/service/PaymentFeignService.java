package com.rookie.bigdata.service;

import com.rookie.bigdata.entities.CommonResult;
import com.rookie.bigdata.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname PaymentFeignService
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/8 15:34
 * @Version 1.0
 */
@Component
@FeignClient(value = "ROOKIE-EUREKA-PAYMENT")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
