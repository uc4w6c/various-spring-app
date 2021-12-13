package com.example.resilience4jserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
  @GetMapping("hello")
  public String hello() {
    return "Server Hello!";
  }

  @GetMapping("failure")
  public String failure() {
    throw new RuntimeException("error");
  }
}
