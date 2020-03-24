package com.example.kafka.controller;

import com.example.kafka.config.KafkaProducer;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @EnableConfigurationProperties
@RequestMapping("/sample")
public class SampleController {
    private KafkaTemplate kafkaTemplate;

    SampleController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory,
            KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                new DeadLetterPublishingRecoverer(template), new FixedBackOff(0L, 2))); // dead-letter after 3 tries
        return factory;
    }

    /*
    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }
    */

    @GetMapping
    public String send() {
        final Person taro = new Person("Taro", 20);
        this.kafkaTemplate.send("sample.test", taro);
        return "OK";
    }

    @KafkaListener(topics = "sample.test",  groupId = "sample")
    public void listen(Person person) {
        System.out.println("name:" + person.getName() + ", age:" + person.getAge());
    }
}
