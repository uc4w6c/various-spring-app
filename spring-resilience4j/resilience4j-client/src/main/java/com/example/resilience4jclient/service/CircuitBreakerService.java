package com.example.resilience4jclient.service;

import com.example.resilience4jclient.repository.BackendARepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {
  private BackendARepository backendARepository;
  private Logger logger;

  public CircuitBreakerService(BackendARepository backendARepository, Logger logger) {
    this.backendARepository = backendARepository;
    this.logger = logger;
  }

  @CircuitBreaker(name = "backendA")
  public String success() {
    logger.info("CircuitBreaker success method start");
    return backendARepository.hello();
  }

  @CircuitBreaker(name = "backendA")
  public String failure() {
    logger.info("CircuitBreaker failure method start");
    return backendARepository.failure();
  }
}
