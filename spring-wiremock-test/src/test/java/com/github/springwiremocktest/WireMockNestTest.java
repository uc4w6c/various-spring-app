package com.github.springwiremocktest;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 8765)
class WireMockNestTest {
  @Test
  void runs_on_the_supplied_port(WireMockRuntimeInfo wmRuntimeInfo) {
    assertThat(wmRuntimeInfo.getHttpPort()).isEqualTo(8765);
  }

  @Nested
  @WireMockTest(httpPort = 8766)
  class RunsOn8766 {
    @Test
    void runs_on_the_supplied_port(WireMockRuntimeInfo wmRuntimeInfo) {
      assertThat(wmRuntimeInfo.getHttpPort()).isEqualTo(8766);
    }
  }
}
