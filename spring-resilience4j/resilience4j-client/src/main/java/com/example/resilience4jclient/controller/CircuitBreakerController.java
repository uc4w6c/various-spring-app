package com.example.resilience4jclient.controller;

import com.example.resilience4jclient.service.CircuitBreakerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/circuitbreaker")
public class CircuitBreakerController {
  private CircuitBreakerService circuitBreakerService;

  public CircuitBreakerController(CircuitBreakerService circuitBreakerService) {
    this.circuitBreakerService = circuitBreakerService;
  }

  @GetMapping
  @RequestMapping("success")
  public String success() {
    return circuitBreakerService.success();
  }

  @GetMapping
  @RequestMapping("failure")
  public String failure() {
    return circuitBreakerService.failure();
  }
}
