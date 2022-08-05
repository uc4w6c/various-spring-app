package com.github.springwiremocktest.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

@WireMockTest
public class BasicStubbing {
  @Test
  void stubing(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(get("/hello").willReturn(aResponse().withStatus(200).withBody("Hello World!")));

    WebTestClient webTestClient =
        WebTestClient.bindToServer().baseUrl(wmRuntimeInfo.getHttpBaseUrl()).build();

    webTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello World!");
  }
}
