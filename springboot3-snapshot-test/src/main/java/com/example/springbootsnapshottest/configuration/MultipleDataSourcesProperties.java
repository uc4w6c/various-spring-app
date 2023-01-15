package com.example.springbootsnapshottest.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "spring")
public record MultipleDataSourcesProperties(Map<String, DataSourceProperties> datasource) {}
