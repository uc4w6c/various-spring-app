package com.github.springwiremocktest.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

public class Templating {
  @RegisterExtension
  static WireMockExtension wireMockExtension =
      new WireMockExtension.Builder()
          .options(
              WireMockConfiguration.wireMockConfig()
                  .dynamicPort()
                  .extensions(new ResponseTemplateTransformer(true)))
          .build();

  @Test
  void templated() {
    wireMockExtension.stubFor(
        get("/templated")
            .willReturn(
                aResponse()
                    .withBody("{{request.headers.X-Request-Id}}")
                    .withTransformers("respones-template")));

    WebTestClient webTestClient =
        WebTestClient.bindToServer()
            .baseUrl(wireMockExtension.getRuntimeInfo().getHttpBaseUrl())
            .build();

    webTestClient
        .get()
        .uri("/templated")
        .header("X-Request-Id", "123")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .equals("123");
  }
}
