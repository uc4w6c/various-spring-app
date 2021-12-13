package com.example.resilience4jclient.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.example.resilience4jclient.repository.BackendARepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {
  private BackendARepository backendARepository;

  public CircuitBreakerService(BackendARepository backendARepository) {
    this.backendARepository = backendARepository;
  }

  @CircuitBreaker(name = "backendA")
  public String success() {
    return backendARepository.hello();
  }

  @CircuitBreaker(name = "backendA")
  public String failure() {
    return backendARepository.failure();
  }
}
