package com.rookie.bigdata.controller;

import com.rookie.bigdata.entities.CommonResult;
import com.rookie.bigdata.entities.Payment;
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

//    @Resource
//    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功,serverPort: "+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:  "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }

//    @GetMapping(value = "/payment/discovery")
//    public Object discovery()
//    {
//        List<String> services = discoveryClient.getServices();
//        for (String element : services) {
//            log.info("*****element: "+element);
//        }
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//        for (ServiceInstance instance : instances) {
//            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
//        }
//
//        return this.discoveryClient;
//    }
//
//    @GetMapping(value = "/payment/lb")
//    public String getPaymentLB()
//    {
//        return serverPort;
//    }
//
//    @GetMapping(value = "/payment/feign/timeout")
//    public String paymentFeignTimeout()
//    {
//        // 业务逻辑处理正确，但是需要耗费3秒钟
//        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
//        return serverPort;
//    }
//
//    @GetMapping("/payment/zipkin")
//    public String paymentZipkin()
//    {
//        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
//    }


}