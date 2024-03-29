package com.github.springwiremocktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringWiremockTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringWiremockTestApplication.class, args);
  }
}
