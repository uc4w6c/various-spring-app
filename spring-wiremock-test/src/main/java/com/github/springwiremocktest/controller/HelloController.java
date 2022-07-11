package com.github.springwiremocktest.controller;

import com.github.springwiremocktest.entity.HelloEntity;
import com.github.springwiremocktest.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
  private HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping
  public HelloEntity index() {
    return helloService.call();
  }
}
