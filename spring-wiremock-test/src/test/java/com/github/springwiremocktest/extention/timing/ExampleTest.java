package com.github.springwiremocktest.extention.timing;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Example(name = "root")
public class ExampleTest {
  @Test
  void root() {
    System.out.println("root");
    assertTrue(true);
  }

  @Nested
  @Example(name = "nest")
  class Nest {
    @Test
    void nest() {
      System.out.println("nest");
      assertTrue(true);
    }
  }
}
