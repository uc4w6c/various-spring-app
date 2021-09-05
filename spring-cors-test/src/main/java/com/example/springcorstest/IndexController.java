package com.example.springcorstest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @CrossOrigin
    @GetMapping
    // @RequestMapping(method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public String index() {
        return "This is Server!";
    }
}
