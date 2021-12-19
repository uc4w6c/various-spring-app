package com.example.resilience4jclient.controller;

import com.example.resilience4jclient.service.BulkheadService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulkhead")
public class BulkheadController {
  private BulkheadService bulkheadService;

  public BulkheadController(BulkheadService bulkheadService) {
    this.bulkheadService = bulkheadService;
  }

  @GetMapping
  public String index() {
    return bulkheadService.delay();
  }
}
