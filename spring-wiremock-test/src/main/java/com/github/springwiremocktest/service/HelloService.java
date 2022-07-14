package com.github.springwiremocktest.service;

import com.github.springwiremocktest.configuration.HelloServerConfiguration;
import com.github.springwiremocktest.entity.HelloEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HelloService {
  private WebClient webClient;

  public HelloService(
      WebClient.Builder webClientBuilder, HelloServerConfiguration helloServerConfiguration) {
    this.webClient = webClientBuilder.baseUrl(helloServerConfiguration.url()).build();
  }

  public HelloEntity call() {
    return webClient
        .get()
        .uri("/hello/")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(HelloEntity.class)
        .block();
  }
}
