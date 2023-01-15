package com.example.springbootsnapshottest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("test")
public record TestProperties(Map<String, String> map) {}
