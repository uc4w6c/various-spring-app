package com.example.multidb.db;

import org.flywaydb.core.Flyway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {
  @Bean
  public Flyway firstFlyway() {

  }
}
