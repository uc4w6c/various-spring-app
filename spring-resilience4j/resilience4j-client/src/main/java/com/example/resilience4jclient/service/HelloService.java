package com.example.resilience4jclient.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
  @CircuitBreaker(name = "backendA")
  public String getHello() {
    HttpClient httpClient = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder(
            URI.create("http://localhost:8081/hello"))
        // .header("accept", "application/json")
        .build();

    try {
      HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
      return response.body();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
