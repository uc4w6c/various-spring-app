package com.example.demo.service

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun say(): String {
        return "Hello!"
    }
}
