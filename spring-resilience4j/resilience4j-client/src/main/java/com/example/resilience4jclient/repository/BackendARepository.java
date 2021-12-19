package com.example.resilience4jclient.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BackendARepository {
  private RestTemplate restTemplate;

  public BackendARepository(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String hello() {
    return restTemplate.getForObject("http://localhost:8081/hello", String.class);
  }

  public String failure() {
    return restTemplate.getForObject("http://localhost:8081/failure", String.class);
  }

  public String delay(int delaySecond) {
    return restTemplate.getForObject("http://localhost:8081/delay/" + delaySecond, String.class);
  }
}
