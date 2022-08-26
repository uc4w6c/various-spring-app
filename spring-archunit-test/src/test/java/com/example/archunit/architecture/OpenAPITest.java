package com.example.archunit.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = "com.example.archunit")
public class OpenAPITest {
  @ArchTest
  public static final ArchRule リクエストを受け付けるメソッドにOpenAPIアノテーションを書くこと =
      methods()
          .that()
          .areAnnotatedWith(GetMapping.class)
          .or()
          .areAnnotatedWith(PostMapping.class)
          .or()
          .areAnnotatedWith(PutMapping.class)
          .or()
          .areAnnotatedWith(PatchMapping.class)
          .or()
          .areAnnotatedWith(DeleteMapping.class)
          // チェック内容
          .should()
          .beAnnotatedWith(Operation.class)
          .andShould()
          .beAnnotatedWith(ApiResponse.class);
}
