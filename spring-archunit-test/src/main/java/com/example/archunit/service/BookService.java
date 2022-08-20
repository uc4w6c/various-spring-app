package com.example.archunit.service;

import com.example.archunit.entity.BookEntity;
import com.example.archunit.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
