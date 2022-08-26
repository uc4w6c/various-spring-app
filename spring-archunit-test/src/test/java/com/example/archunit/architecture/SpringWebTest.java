package com.example.archunit.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.example.archunit")
public class SpringWebTest {
  @ArchTest
  public static final ArchRule TransactionalアノテーションはServiceにのみ設定可能 =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .resideOutsideOfPackage("com.example.archunit.service..")
          .should()
          .notBeAnnotatedWith(Transactional.class);

  @ArchTest
  public static final ArchRule ControllerアノテーションはControllerのみに設定可能 =
      classes()
          .that()
          .resideOutsideOfPackage("com.example.archunit.controller..")
          .should()
          .notBeAnnotatedWith(RestController.class);

  @ArchTest
  public static final ArchRule v1パッケージ内のPathはv1で始まる =
      classes()
          .that()
          .resideInAPackage("com.example.archunit.controller.v1..")
          .should()
          .beAnnotatedWith(RestController.class)
          .andShould(havePathStartingWith("v1"));

  private static ArchCondition<JavaClass> havePathStartingWith(String apiVersion) {
    return new ArchCondition<JavaClass>("APIのpathは /" + apiVersion + "/で始める必要があります。") {
      @Override
      public void check(JavaClass item, ConditionEvents events) {
        RequestMapping requestMapping = item.getAnnotationOfType(RequestMapping.class);
        Set<String> pathList =
            Set.of(ArrayUtils.addAll(requestMapping.value(), requestMapping.path()));

        boolean isError =
            pathList.stream().anyMatch(path -> !path.startsWith("/" + apiVersion + "/"));

        if (isError) {
          String message =
              String.format(
                  "Class <%s> の@RequstMapping には /%s を含めてください。 (%s)",
                  item.getName(), apiVersion, item.getSimpleName() + ".java:0");
          events.add(SimpleConditionEvent.violated(item, message));
        }
      }
    };
  }
}
