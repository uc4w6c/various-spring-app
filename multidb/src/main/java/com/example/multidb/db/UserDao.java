package com.example.multidb.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
  private JdbcTemplate jdbcTemplate;

  public UserDao(@Qualifier("firstJdbcTemplate") JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public String getName(String id) {
    return jdbcTemplate.queryForObject("select name from users where id = ?", String.class, id);
  }
}
