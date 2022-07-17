package com.github.springwiremocktest.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@WireMockTest
public abstract class AbstractIntegrationTest {
  private static WireMockServer wireMockServer;

  static {
    Options options = WireMockConfiguration.wireMockConfig().dynamicPort();
    wireMockServer = new WireMockServer(options);
    wireMockServer.start();

    configureFor(wireMockServer.port());
  }

  @DynamicPropertySource
  static void setup(DynamicPropertyRegistry registry) {
    // registry.add("api.stock.url", wmRuntimeInfo::getHttpBaseUrl);
    registry.add("api.stock.url", wireMockServer::baseUrl);
  }
}
