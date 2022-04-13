package com.rookie.bigdata.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


/**
 * @Classname PaymentController
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/6 14:45
 * @Version 1.0
 */
@Component
@EnableBinding(Sink.class)
public class OrderController {

    protected final Logger log = LoggerFactory.getLogger(OrderController.class);


    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者1号,----->接受到的消息: " + message.getPayload() + "\t  port: " + serverPort);

    }
}

