package com.github.springwiremocktest.dao.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springwiremocktest.configuration.HelloServerConfiguration;
import com.github.springwiremocktest.configuration.StockConfiguration;
import com.github.springwiremocktest.entity.HelloEntity;
import com.github.springwiremocktest.entity.StockEntity;
import com.github.springwiremocktest.entity.StockReduceEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

  public void reduce(StockReduceEntity stockReduceEntity) {
    webClient
        .post()
        .uri("/stock/products/reduce")
        .body(Mono.just(stockReduceEntity), StockReduceEntity.class)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(void.class)
        .block();
  }
}
