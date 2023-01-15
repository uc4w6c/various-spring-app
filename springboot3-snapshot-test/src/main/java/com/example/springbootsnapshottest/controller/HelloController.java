package com.example.springbootsnapshottest.controller;

import com.example.springbootsnapshottest.configuration.MultipleDataSourcesProperties;
import com.example.springbootsnapshottest.configuration.TestProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
  private MultipleDataSourcesProperties multipleDataSourcesProperties;
  private TestProperties testProperties;

  public HelloController(MultipleDataSourcesProperties multipleDataSourcesProperties, TestProperties testProperties) {
    this.multipleDataSourcesProperties = multipleDataSourcesProperties;
    this.testProperties = testProperties;
  }

  @GetMapping
  public String index() {
    System.out.println(multipleDataSourcesProperties);
    System.out.println(testProperties);
    return "Hello";
  }
}
