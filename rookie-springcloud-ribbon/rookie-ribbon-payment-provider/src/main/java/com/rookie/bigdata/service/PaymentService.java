package com.rookie.bigdata.service;

import com.rookie.bigdata.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname PaymentService
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:48
 * @Version 1.0
 */
public interface PaymentService
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
