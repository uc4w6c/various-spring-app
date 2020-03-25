package com.example.demo.application.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping()
class HelloController() {
    @GetMapping
    fun index(): String {
        return "Hello!"
    }

    @PostMapping
    fun create(): String {
        return "Hello!"
    }
}
