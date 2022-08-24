package com.example.archunit.architecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.example.archunit")
public class LayerTest {
  @ArchTest
  public static final ArchRule レイヤー間の依存関係チェック =
      layeredArchitecture()
          .consideringAllDependencies()
          .layer("Controller").definedBy("com.example.archunit.controller..")
          .layer("Service").definedBy("com.example.archunit.service..")
          .layer("Repository").definedBy("com.example.archunit.repository..")

          .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
          .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
          .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
}
