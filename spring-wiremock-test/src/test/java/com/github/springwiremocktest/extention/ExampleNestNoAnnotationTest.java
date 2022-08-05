package com.github.springwiremocktest.extention;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Example(name = "root")
public class ExampleNestNoAnnotationTest {
  @Test
  void root() {
    System.out.println("root");
    assertTrue(true);
  }

  @Nested
  class Nest {
    @Test
    void nest() {
      System.out.println("nest");
      assertTrue(true);
    }
  }
}
