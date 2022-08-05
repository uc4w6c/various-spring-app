package com.github.springwiremocktest.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

@WireMockTest
public class SimulationgFaults {
  @Test
  void timeout(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(get("/delayed").willReturn(aResponse().withStatus(200).withFixedDelay(3000)));

    WebTestClient webTestClient =
        WebTestClient.bindToServer()
            .baseUrl(wmRuntimeInfo.getHttpBaseUrl())
            .responseTimeout(Duration.ofMillis(2000))
            .build();

    assertThatThrownBy(
            () -> {
              webTestClient.get().uri("/delayed").exchange().expectStatus().isOk();
            })
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Timeout");
  }
}
