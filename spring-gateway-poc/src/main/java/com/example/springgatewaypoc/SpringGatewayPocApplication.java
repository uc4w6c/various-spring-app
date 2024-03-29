package com.example.springgatewaypoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class SpringGatewayPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayPocApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p ->
						p.path("/get")
							.filters(f -> f.addRequestHeader("Hello", "World"))
							.uri("http://httpbin.org:80"))
				.route(p ->
						p.host("*.circuitbreaker.com")
								.filters(f -> f.circuitBreaker(config ->
										config.setName("mycmd")
											.setFallbackUri("forward:/fallback")))
								.uri("http://httpbin.org:80"))
				.build();
	}

	@GetMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}
}
