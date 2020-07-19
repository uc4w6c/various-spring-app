package com.example.thymeleaftest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("from_num", 1);
        model.addAttribute("to_num", 10);

        var messages = List.of("aa", "bb");
        model.addAttribute("messages", messages);
        return "index";
    }

}
