package com.example.demo.controller

import com.example.demo.service.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class HelloController(
    private val helloService: HelloService
) {
    @GetMapping()
    fun getHello(): String {
        return helloService.say()
    }
}
