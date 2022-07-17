package com.github.springwiremocktest.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.springwiremocktest.entity.StockEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest extends AbstractIntegrationTest {
  @Autowired private WebTestClient webTestClient;

  @Test
  void 商品を取得できる() {
    int quantity = 10;
    StockEntity stockEntity = new StockEntity("1", quantity);
    stubFor(get("/stock/products/1").willReturn(jsonResponse(stockEntity, 200)));

    webTestClient
        .get()
        .uri("/products/{id}", "1")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.id")
        .isEqualTo(1)
        .jsonPath("$.price")
        .isEqualTo(1000)
        .jsonPath("$.quantity")
        .isEqualTo(quantity);
  }
}
