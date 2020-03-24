package com.example.kafka.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
// @EnableConfigurationProperties
public class KafkaProducer {
    /*
    private KafkaTemplate<String,String> kafkaTemplate;

    KafkaProducer(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendKafka(String message) {
        System.out.println("send:" + message);
        kafkaTemplate.send("test1" ,message);
    }
    */
}
