package com.github.springwiremocktest.extention;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class ExampleExtension2
    implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

  private String name;

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    System.out.println(name + ":end");
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {}

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    Example example =
        AnnotationSupport.findAnnotation(context.getRequiredTestClass(), Example.class)
            .orElse(null);
    if (example == null) return;

    if (name == null) {
      name = example.name();
    }
    System.out.println(name + ":start");
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {}
}
