package com.example.multidb.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
  @Qualifier("firstJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  public UserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public String getName(String id) {
    RowMapper<String> rowMapper = new BeanPropertyRowMapper<String>(String.class);
    return jdbcTemplate.queryForObject("select name from users where id = ?", rowMapper, id);
  }
}
