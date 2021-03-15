package com.example.flyway.service;

import java.util.List;

import com.example.flyway.model.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private JdbcTemplate jdbcTemplate;
	public UserService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<User> getUsers() {
		List<User> users = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
		users.forEach(System.out::println);
		return users;
	}
}
