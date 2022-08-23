package com.example.archunit.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.example.archunit")
public class ClassNamingTest {
  @ArchTest
  public static final ArchRule クラス名がControlerで終わる =
      classes()
          .that()
          .resideInAPackage("com.example.archunit.controller..")
          .should()
          .haveSimpleNameEndingWith("Controller");
}
