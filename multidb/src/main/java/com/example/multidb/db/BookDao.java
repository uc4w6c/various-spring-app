package com.example.multidb.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDao {
  private JdbcTemplate jdbcTemplate;

  public BookDao(@Qualifier("secondJdbcTemplate") JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public String getName(String id) {
    return jdbcTemplate.queryForObject("select name from books where id = ?", String.class, id);
  }
}
