package com.github.springwiremocktest.service;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.github.springwiremocktest.configuration.HelloServerConfiguration;
import com.github.springwiremocktest.entity.HelloEntity;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@WireMockTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloServiceTest {
  @Autowired private WebClient.Builder webClientBuilder;

  /**
   * 単純なGETメソッドの呼び出しテスト
   *
   * @param wmRuntimeInfo
   */
  @Test
  void 正常にレスポンスする(WireMockRuntimeInfo wmRuntimeInfo) {
    HelloEntity mockEntity = new HelloEntity("takashi", 30);
    stubFor(get("/hello/").willReturn(jsonResponse(mockEntity, 200)));

    String baseUrl = wmRuntimeInfo.getHttpBaseUrl();
    HelloServerConfiguration helloServerConfiguration = new HelloServerConfiguration(baseUrl);

    HelloService helloService = new HelloService(webClientBuilder, helloServerConfiguration);

    HelloEntity actual = helloService.call();
    assertThat(actual.name()).isEqualTo("takashi");
    assertThat(actual.age()).isEqualTo(30);
  }
}
