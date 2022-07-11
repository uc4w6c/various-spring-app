package com.github.springwiremocktest;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HelloService {
  private WebClient webClient;

  public HelloService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();;
  }

  public String call() {
    return webClient.get().uri("/hello/")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }
}
