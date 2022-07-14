package com.github.springwiremocktest.dao.api;

import com.github.springwiremocktest.configuration.HelloServerConfiguration;
import com.github.springwiremocktest.configuration.StockConfiguration;
import com.github.springwiremocktest.entity.HelloEntity;
import com.github.springwiremocktest.entity.StockEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StockDao {
  private WebClient webClient;

  public StockDao(WebClient.Builder webClientBuilder, StockConfiguration stockConfiguration) {
    this.webClient = webClientBuilder.baseUrl(stockConfiguration.url()).build();
  }

  public StockEntity findById(String id) {
    return webClient
        .get()
        .uri("/stock/products/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(StockEntity.class)
        .block();
  }
}
