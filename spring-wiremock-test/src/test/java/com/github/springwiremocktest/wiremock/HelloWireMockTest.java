package com.github.springwiremocktest.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WireMockTest
public class HelloWireMockTest {
  @Test
  void hello(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(get("/hello").willReturn(okJson("{ \"message\": \"hello\" }")));
    String baseUrl = wmRuntimeInfo.getHttpBaseUrl();

    WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();

    webTestClient
        .get()
        .uri("hello")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("hello");
  }
}
