package com.github.springwiremocktest.extention;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Example(name = "root")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
