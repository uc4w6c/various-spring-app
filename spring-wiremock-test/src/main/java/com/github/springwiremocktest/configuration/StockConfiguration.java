package com.github.springwiremocktest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.stock")
public record StockConfiguration(String url) {}
