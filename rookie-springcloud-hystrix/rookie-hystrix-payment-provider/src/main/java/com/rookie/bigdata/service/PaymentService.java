package com.rookie.bigdata.service;


/**
 * @Classname PaymentService
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:48
 * @Version 1.0
 */
public interface PaymentService {
    String paymentInfo_OK(Integer id);

    String paymentInfo_TimeOut(Integer id);


    String paymentInfo_TimeOutHandler(Integer id);
}
