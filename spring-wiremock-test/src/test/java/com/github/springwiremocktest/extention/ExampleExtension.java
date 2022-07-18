package com.github.springwiremocktest.extention;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class ExampleExtension implements BeforeEachCallback,
    BeforeAllCallback,
    AfterEachCallback,
    AfterAllCallback {

  private static final String NAME_STORE = "store";

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass()));
    System.out.println(store.get(NAME_STORE) + ":end");
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    Example example = AnnotationSupport.findAnnotation(context.getRequiredTestClass(), Example.class).orElse(null);
    if (example == null) return;

    ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass()));

    String name = store.get(NAME_STORE, String.class);
    if (name == null) {
      name = example.name();
      store.put(NAME_STORE, name);
    }
    System.out.println(name + ":start");
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
  }
}
