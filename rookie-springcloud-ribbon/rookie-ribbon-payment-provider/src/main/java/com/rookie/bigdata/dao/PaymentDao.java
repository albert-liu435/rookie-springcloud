package com.rookie.bigdata.dao;

import com.rookie.bigdata.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname PaymentDao
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:49
 * @Version 1.0
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}