package com.rookie.bigdata.controller;

import com.rookie.bigdata.entities.CommonResult;
import com.rookie.bigdata.entities.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;


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
    //public static final String PAYMENT_URL = "http://localhost:8001";
    // @LoadBalanced负载均衡
    public static final String PAYMENT_URL = "http://ROOKIE-EUREKA-PAYMENT";

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

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


    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id)
    {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }


//    @GetMapping(value = "/consumer/payment/lb")
//    public String getPaymentLB()
//    {
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//
//        if(instances == null || instances.size() <= 0)
//        {
//            return null;
//        }
//
//        ServiceInstance serviceInstance = loadBalancer.instances(instances);
//        URI uri = serviceInstance.getUri();
//
//        return restTemplate.getForObject(uri+"/payment/lb",String.class);
//
//    }

}
