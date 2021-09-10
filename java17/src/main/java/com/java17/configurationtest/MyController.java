package com.java17.configurationtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/")
public class MyController {
    private MyProperties myProperties;

    public MyController(MyProperties myProperties) {
        this.myProperties = myProperties;
    }

    @GetMapping
    public MyProperties index() {
        return myProperties;
    }
}
