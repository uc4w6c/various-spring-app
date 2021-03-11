package com.example.flyway.controller;

import java.util.List;

import com.example.flyway.model.User;
import com.example.flyway.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private UserService userService;

	public HelloController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> index() {
		return this.userService.getUsers();
	}
}
