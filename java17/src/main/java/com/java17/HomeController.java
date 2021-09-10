package com.java17;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    private TestProperties testProperties;
    private TestProperties2 testProperties2;

    public HomeController(TestProperties testProperties, TestProperties2 testProperties2) {
        this.testProperties = testProperties;
        this.testProperties2 = testProperties2;
    }

    @GetMapping
    public GreetResponse index() {
        var greetResponse = new GreetResponse(testProperties.name(), testProperties.age(), true, false);
        String testMessage = """
                こんにちは
                これはテストメッセージです。
                """;
        System.out.println(testMessage);
        System.out.println(testProperties2.getName() + ":" + testProperties2.getAge());
        return greetResponse;
    }
}
