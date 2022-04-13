package com.rookie.bigdata.controller;

import com.rookie.bigdata.service.IMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}
