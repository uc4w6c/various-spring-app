package com.example.springresourceserverpoc;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8080)
class HelloControllerTest {
  /*@Bean
  public JwtDecoder jwtDecoder() {
    return JwtDecoders.fromIssuerLocation("http://localhost:8080");
  }*/

  @Autowired
  private JWKSource<SecurityContext> jwtSource;
  @Autowired
  private JwtEncoder jwtEncoder;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void scopeが正しい場合はsubjectを返却() {
    stubFor(get("/.well-known/openid-configuration")
        .willReturn(okJson("""
            {
              "issuer": "http://localhost:8080",
              "jwks_uri": "http://localhost:8080/oauth2/jwks"
            }
            """)));

    JWKSelector selector = new JWKSelector(new JWKMatcher.Builder().build());
    JWKSet jwkSet = null;
    try {
      jwkSet = new JWKSet(this.jwtSource.get(selector, null));
    } catch (KeySourceException e) {
      throw new RuntimeException(e);
    }
    stubFor(get("/oauth2/jwks").willReturn(okJson(jwkSet.toString())));

    JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();
    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .subject("taro")
        .issuer("http://localhost:8080")
        .claim("scope", "message.read")
        .build();

    Jwt jwt = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claimsSet));
    String token = jwt.getTokenValue();

    webTestClient.get()
        .uri("/")
        .headers(headers -> headers.setBearerAuth(token))
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class).isEqualTo("taro");
  }

  @Test
  void scopeが異なる場合は403を返却() {
    stubFor(get("/.well-known/openid-configuration")
        .willReturn(okJson("""
            {
              "issuer": "http://localhost:8080",
              "jwks_uri": "http://localhost:8080/oauth2/jwks"
            }
            """)));

    JWKSelector selector = new JWKSelector(new JWKMatcher.Builder().build());
    JWKSet jwkSet = null;
    try {
      jwkSet = new JWKSet(this.jwtSource.get(selector, null));
    } catch (KeySourceException e) {
      throw new RuntimeException(e);
    }
    stubFor(get("/oauth2/jwks").willReturn(okJson(jwkSet.toString())));

    JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();
    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .subject("taro")
        .issuer("http://localhost:8080")
        .claim("scope", "message.write")
        .build();

    Jwt jwt = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claimsSet));
    String token = jwt.getTokenValue();

    webTestClient.get()
        .uri("/")
        .headers(headers -> headers.setBearerAuth(token))
        .exchange()
        .expectStatus().isForbidden();
  }
}
