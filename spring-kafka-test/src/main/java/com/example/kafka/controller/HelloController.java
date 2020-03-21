package com.example.kafka.controller;

import com.example.kafka.config.KafkaProducer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @EnableConfigurationProperties
@RequestMapping("/")
public class HelloController {
    private KafkaProducer kafkaProducer;

    HelloController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public String hello() {
        kafkaProducer.sendKafka("Hello Kafka");
        return "Hello World!";
    }

    @KafkaListener(id = "client", topics = "test1",groupId = "sample")
    public void listen(String message) {
        System.out.println("message:" + message);
    }
}
