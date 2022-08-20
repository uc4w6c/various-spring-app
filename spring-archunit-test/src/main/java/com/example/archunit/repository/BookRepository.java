package com.example.archunit.repository;

import com.example.archunit.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository {
  private Map<Long, BookEntity> books = Map.of(1L, new BookEntity(1, "プロになるJava"),
      2L, new BookEntity(1, "Effective Java"));

  public List<BookEntity> findAll() {
    return books.values().stream().toList();
  }

  public Optional<BookEntity> findById(long id) {
    if (!books.containsKey(id)) {
      return Optional.empty();
    }
    return Optional.of(books.get(id));
  }
}
