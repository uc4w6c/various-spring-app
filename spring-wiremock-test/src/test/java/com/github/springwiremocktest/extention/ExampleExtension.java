package com.github.springwiremocktest.extention;

import java.util.Optional;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.AnnotationUtils;

public class ExampleExtension
    implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

  private static final String NAME_STORE = "store";

  public ExampleExtension() {
    System.out.println("constructor start");
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store store =
        context.getStore(
            ExtensionContext.Namespace.create(getClass(), context.getRequiredTestClass()));
    System.out.println(store.get(NAME_STORE) + ":end");
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {}

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    // Example example = AnnotationSupport.findAnnotation(context.getRequiredTestClass(),
    // Example.class).orElse(null);
    Example example = findAnnotation(context);
    if (example == null) return;

    ExtensionContext.Store store =
        context.getStore(
            ExtensionContext.Namespace.create(getClass(), context.getRequiredTestClass()));

    String name = store.get(NAME_STORE, String.class);
    if (name == null) {
      name = example.name();
      store.put(NAME_STORE, name);
    }
    System.out.println(name + ":start");
  }

  private Example findAnnotation(ExtensionContext context) {
    Optional<ExtensionContext> current = Optional.of(context);
    while (current.isPresent()) {
      Optional<Example> example =
          AnnotationUtils.findAnnotation(current.get().getRequiredTestClass(), Example.class);
      if (example.isPresent()) {
        return example.get();
      }
      current = current.get().getParent();
    }
    return null;
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {}
}
