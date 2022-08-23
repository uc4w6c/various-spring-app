package com.example.archunit.controller;

import com.example.archunit.entity.BookEntity;
import com.example.archunit.exceptions.NotFoundException;
import com.example.archunit.repository.BookRepository;
import com.example.archunit.service.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@Validated
public class BookController {
  private BookService bookService;
  private BookRepository bookRepository;

  public BookController(BookService bookService, BookRepository bookRepository) {
    this.bookService = bookService;
    this.bookRepository = bookRepository;
  }

  @GetMapping
  public List<BookEntity> index() {
    return bookService.findAll();
  }

  @GetMapping("{id}")
  public BookEntity findById(@PathVariable("id") @NonNull long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("指定した本は見つかりませんでした。"));
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  @ResponseBody
  public Map<String, Object> handleNotFoundException(NotFoundException exception) {
    Map<String, Object> errorMap = new HashMap<String, Object>();
    errorMap.put("message", exception.getMessage());
    return errorMap;
  }
}
