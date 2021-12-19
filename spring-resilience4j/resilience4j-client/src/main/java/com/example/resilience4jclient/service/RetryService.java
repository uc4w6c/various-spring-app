package com.example.resilience4jclient.service;

import com.example.resilience4jclient.repository.BackendARepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class RetryService {
  private BackendARepository backendARepository;
  private Logger logger;

  public RetryService(BackendARepository backendARepository, Logger logger) {
    this.backendARepository = backendARepository;
    this.logger = logger;
  }

  @Retry(name = "backendA")
  public String retry() {
    logger.info("retry method start");
    return backendARepository.failure();
  }
}
