package com.example.resilience4jclient.controller;

import com.example.resilience4jclient.service.RetryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
public class RetryController {
  private RetryService retryService;

  public RetryController(RetryService retryService) {
    this.retryService = retryService;
  }

  @GetMapping
  public String index() {
    return retryService.retry();
  }
}
