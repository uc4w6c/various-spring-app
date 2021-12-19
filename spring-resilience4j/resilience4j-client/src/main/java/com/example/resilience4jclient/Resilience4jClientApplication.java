package com.example.resilience4jclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Resilience4jClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jClientApplication.class, args);
	}

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  @Scope("prototype")
  Logger logger(InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(
        injectionPoint.getMethodParameter().getContainingClass());
  }
}
