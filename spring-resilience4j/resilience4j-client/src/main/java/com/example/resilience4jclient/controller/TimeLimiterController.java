package com.example.resilience4jclient.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.example.resilience4jclient.service.TimeLimiterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timeLimiter")
public class TimeLimiterController {
  private TimeLimiterService timeLimiterService;

  public TimeLimiterController(TimeLimiterService timeLimiterService) {
    this.timeLimiterService = timeLimiterService;
  }

  @GetMapping("success")
  public CompletableFuture<String> success() {
    return timeLimiterService.success();
  }

  @GetMapping("timeout")
  public CompletableFuture<String> timeout() {
    return timeLimiterService.timeout();
  }
}
