package com.example.resilience4jclient.service;

import com.example.resilience4jclient.repository.BackendARepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class BulkheadService {
  private BackendARepository backendARepository;
  private Logger logger;

  public BulkheadService(BackendARepository backendARepository, Logger logger) {
    this.backendARepository = backendARepository;
    this.logger = logger;
  }

  @Bulkhead(name = "backendA")
  public String delay() {
    logger.info("delay method start");
    return backendARepository.delay(5);
  }
}
