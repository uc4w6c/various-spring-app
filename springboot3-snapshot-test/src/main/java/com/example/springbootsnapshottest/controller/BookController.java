package com.example.springbootsnapshottest.controller;

import com.example.springbootsnapshottest.controller.request.BookRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@Validated
public class BookController {
  @GetMapping("{id}")
  public String index(@PathVariable("id") @Valid @Positive String id) {
    System.out.println(id);
    return "hello";
  }

  @PostMapping
  public String create(@RequestBody BookRequest bookRequest) {
    System.out.println(bookRequest);
    return "OK";
  }
}
