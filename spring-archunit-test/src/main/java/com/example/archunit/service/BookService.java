package com.example.archunit.service;

import com.example.archunit.entity.BookEntity;
import com.example.archunit.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BookService {
  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }
}
