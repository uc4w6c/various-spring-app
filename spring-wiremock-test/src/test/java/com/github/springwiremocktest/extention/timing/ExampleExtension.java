package com.github.springwiremocktest.extention.timing;

import org.junit.jupiter.api.extension.*;

public class ExampleExtension
    implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

  public ExampleExtension() {
    System.out.println("constructor start");
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    System.out.println("after all");
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    System.out.println("after each");
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    System.out.println("before all:" + context.getTestClass());
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    System.out.println("before each:" + context.getTestClass());
  }
}
