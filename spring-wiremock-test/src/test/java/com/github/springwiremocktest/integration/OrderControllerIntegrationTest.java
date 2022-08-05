package com.github.springwiremocktest.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.springwiremocktest.controller.request.OrderPurchaseRequest;
import com.github.springwiremocktest.entity.StockEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest extends AbstractIntegrationTest {
  @Autowired private WebTestClient webTestClient;

  @Test
  void 商品を取得できる() {
    int quantity = 10;
    StockEntity stockEntity = new StockEntity("1", quantity);
    stubFor(post("/stock/products/reduce").willReturn(created()));

    OrderPurchaseRequest orderPurchaseRequest = new OrderPurchaseRequest("1", 2);
    webTestClient
        .post()
        .uri("/order/", "1")
        .body(Mono.just(orderPurchaseRequest), OrderPurchaseRequest.class)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isCreated();

    verify(1, postRequestedFor(urlEqualTo("/stock/products/reduce")));
  }
}
