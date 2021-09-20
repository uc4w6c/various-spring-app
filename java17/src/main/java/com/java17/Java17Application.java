package com.java17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Java17Application {

  public static void main(String[] args) {
    SpringApplication.run(Java17Application.class, args);
  }
}
