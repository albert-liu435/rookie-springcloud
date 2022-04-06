package com.rookie.bigdata.service.impl;

import com.rookie.bigdata.dao.PaymentDao;
import com.rookie.bigdata.entities.Payment;
import com.rookie.bigdata.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname PaymentServiceImpl
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:49
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
