package com.example.resilience4jclient.service;

import java.util.concurrent.CompletableFuture;

import com.example.resilience4jclient.repository.BackendARepository;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;

import org.springframework.stereotype.Component;

@Component
public class TimeLimiterService {
  private BackendARepository backendARepository;
  private Logger logger;

  public TimeLimiterService(BackendARepository backendARepository, Logger logger) {
    this.backendARepository = backendARepository;
    this.logger = logger;
  }

  @TimeLimiter(name = "backendA")
  public CompletableFuture<String> success() {
    logger.info("TimeLimiter success method start");
    return CompletableFuture.supplyAsync(() -> backendARepository.delay(3));
  }

  @TimeLimiter(name = "backendA")
  public CompletableFuture<String> timeout() {
    logger.info("TimeLimiter timeout method start");
    return CompletableFuture.supplyAsync(() -> backendARepository.delay(5));
  }
}
