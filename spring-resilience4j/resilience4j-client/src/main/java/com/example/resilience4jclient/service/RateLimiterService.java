package com.example.resilience4jclient.service;

import com.example.resilience4jclient.repository.BackendARepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {
  private BackendARepository backendARepository;
  private Logger logger;

  public RateLimiterService(BackendARepository backendARepository, Logger logger) {
    this.backendARepository = backendARepository;
    this.logger = logger;
  }

  @RateLimiter(name = "backendA")
  public String rateLimit() {
    logger.info("rateLimit method start");
    return backendARepository.hello();
  }
}
