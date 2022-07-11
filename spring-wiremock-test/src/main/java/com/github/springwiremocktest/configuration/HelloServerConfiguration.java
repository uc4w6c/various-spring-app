package com.github.springwiremocktest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.hello-server")
public record HelloServerConfiguration(String url) {}
