package com.example.multidb.db;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {
  @Bean(name = "firstFlyway", initMethod = "migrate")
  public Flyway createFirstFlyway(@Qualifier("firstDataSource") DataSource dataSource) {
    return new Flyway(
        new FluentConfiguration().locations("db/migration/first").dataSource(dataSource));
  }

  @Bean(name = "secondFlyway", initMethod = "migrate")
  public Flyway createSecondFlyway(@Qualifier("secondDataSource") DataSource dataSource) {
    return new Flyway(
        new FluentConfiguration().locations("db/migration/second").dataSource(dataSource));
  }
}
