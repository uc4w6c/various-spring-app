package com.github.springwiremocktest.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WireMockTest
public class Matching {
  @Test
  void urlRegex(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(get("/users/*").atPriority(10).willReturn(notFound()));
    stubFor(get(urlMatching("/users/(123|456)")).willReturn(okJson("{ \"message\": \"hello\" }")));

    WebTestClient webTestClient =
        WebTestClient.bindToServer().baseUrl(wmRuntimeInfo.getHttpBaseUrl()).build();

    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/users/{id}").build("123"))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("hello");

    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/users/{id}").build("456"))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("hello");

    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/users/{id}").build("789"))
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  void header(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(
        get("/users/123")
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(okJson("{ \"message\": \"hello\" }")));

    stubFor(get("/users/123").withHeader("Accept", equalTo("text/plain")).willReturn(ok("hello")));

    WebTestClient webTestClient =
        WebTestClient.bindToServer().baseUrl(wmRuntimeInfo.getHttpBaseUrl()).build();

    webTestClient
        .get()
        .uri("/users/123")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("hello");

    webTestClient
        .get()
        .uri("/users/123")
        .accept(MediaType.TEXT_PLAIN)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("hello");
  }
}
