package com.spring.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    public void getMessage(Map<String, Object> message) {
        System.out.println("消费者：" + message);
    }
    public void getMessage(Object message) {
        System.out.println("消费者：" + message);
    }

}
