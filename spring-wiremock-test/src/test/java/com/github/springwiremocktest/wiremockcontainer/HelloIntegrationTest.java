package com.github.springwiremocktest.wiremockcontainer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloIntegrationTest extends AbstractIntegrationTest {
  @Autowired private WebTestClient webTestClient;

  @BeforeEach
  void setup() {
    wireMock.removeMappings();
  }

  @Test
  void Taroを返す() {
    stubFor(get("/hello/").willReturn(okJson(" { \"name\": \"taro\", \"age\": 20 }")));

    webTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.name")
        .isEqualTo("taro");
  }

  @Test
  void Hanakoを返す() {
    stubFor(get("/hello/").willReturn(okJson(" { \"name\": \"hanako\", \"age\": 22 }")));

    webTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.name")
        .isEqualTo("hanako");
  }
}
