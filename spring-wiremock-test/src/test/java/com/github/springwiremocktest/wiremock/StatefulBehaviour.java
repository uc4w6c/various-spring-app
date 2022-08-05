package com.github.springwiremocktest.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

@WireMockTest
public class StatefulBehaviour {
  @Test
  void scenario(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(
        get("/hello")
            .inScenario("hello")
            .whenScenarioStateIs(Scenario.STARTED)
            .willReturn(ok("Good Morning"))
            .willSetStateTo("second"));
    stubFor(
        get("/hello")
            .inScenario("hello")
            .whenScenarioStateIs("second")
            .willReturn(ok("Hello"))
            .willSetStateTo("third"));
    stubFor(
        get("/hello")
            .inScenario("hello")
            .whenScenarioStateIs("third")
            .willReturn(ok("Good night")));

    WebTestClient webTestClient =
        WebTestClient.bindToServer().baseUrl(wmRuntimeInfo.getHttpBaseUrl()).build();

    webTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Good Morning");

    webTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello");

    webTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Good night");
  }
}
