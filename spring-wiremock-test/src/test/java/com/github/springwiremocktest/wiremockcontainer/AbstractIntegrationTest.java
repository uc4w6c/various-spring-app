package com.github.springwiremocktest.wiremockcontainer;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

abstract class AbstractIntegrationTest {
  private static final int WIREMOCK_ORIGINAL_PORT = 8080;

  static final GenericContainer<?> wireMockImage =
      new GenericContainer<>(DockerImageName.parse("wiremock/wiremock:2.33.2"))
          .withExposedPorts(WIREMOCK_ORIGINAL_PORT);
  static WireMock wireMock;

  static {
    wireMockImage.start();
    wireMock = new WireMock(wireMockImage.getMappedPort(WIREMOCK_ORIGINAL_PORT));
    WireMock.configureFor(wireMock);
  }

  // ここでapplication.ymlの設定値を変えてAPIの通信先をWireMockに切り替える
  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add(
        "api.hello-server.url",
        () ->
            "http://"
                + wireMockImage.getHost()
                + ":"
                + wireMockImage.getMappedPort(WIREMOCK_ORIGINAL_PORT));
  }
}
