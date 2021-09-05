package com.java17;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    private TestProperties testProperties;

    public HomeController(TestProperties testProperties) {
        this.testProperties = testProperties;
    }

    @GetMapping
    public GreetResponse index() {
        var greetResponse = new GreetResponse(testProperties.name(), testProperties.age());
        return greetResponse;
    }
}
