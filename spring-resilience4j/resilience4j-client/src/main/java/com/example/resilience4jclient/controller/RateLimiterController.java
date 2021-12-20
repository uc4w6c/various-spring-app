package com.example.resilience4jclient.controller;

import com.example.resilience4jclient.service.RateLimiterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rateLimiter")
public class RateLimiterController {
  private RateLimiterService rateLimiterService;

  public RateLimiterController(RateLimiterService rateLimiterService) {
    this.rateLimiterService = rateLimiterService;
  }

  @GetMapping
  public String index() {
    return rateLimiterService.rateLimit();
  }
}
