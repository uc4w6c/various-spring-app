package com.example.multidb.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {
  @Bean("firstDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.first")
  public DataSource createFirstDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "firstJdbcTemplate")
  public JdbcTemplate firstJdbcTemplate(@Qualifier("firstDataSource") DataSource ds) {
    return new JdbcTemplate(ds);
  }

  @Bean("secondDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.second")
  public DataSource createSecondDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "secondJdbcTemplate")
  public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource ds) {
    return new JdbcTemplate(ds);
  }
}
