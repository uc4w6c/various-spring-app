package com.github.springwiremocktest.extention;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExampleRegisterPerClassTest {
  @RegisterExtension private ExampleExtension exampleExtension = new ExampleExtension();

  @Test
  void root() {
    System.out.println("root");
    assertTrue(true);
  }

  @Nested
  class Nest {
    @RegisterExtension private ExampleExtension exampleExtension = new ExampleExtension();

    @Test
    void nest() {
      System.out.println("nest");
      assertTrue(true);
    }
  }
}
