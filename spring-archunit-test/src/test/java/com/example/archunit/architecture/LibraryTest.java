package com.example.archunit.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
    packages = "com.example.archunit",
    importOptions = {ImportOption.OnlyIncludeTests.class})
public class LibraryTest {

  /** これはなし。使っていないかも */
  // @ArchTest
  public static final ArchRule ApacheCommonsLangはApacheCommonsLang3のみを利用可能 =
      classes()
          .should()
          .onlyDependOnClassesThat(
              canDependPackageFindByPackageName(
                  "org.apache.commons.lang", "org.apache.commons.lang3"));

  @ArchTest
  public static final ArchRule StringUtilsはApacheCommonsLang3のみを利用可能 =
      classes()
          .should()
          .onlyDependOnClassesThat(
              canDependPackageFindByClassName("StringUtils", "org.apache.commons.lang3"));

  /**
   * パッケージ名が途中まで同じライブラリの依存先を限定するために利用する 依存するパッケージが指定されたパッケージ名と完全に一致するかを確認する
   *
   * @param prefixPackageName 判断対象パッケージ名(先頭から重複する箇所まで)
   * @param allowPackageName 利用を許可するパッケージ名
   * @return
   */
  private static DescribedPredicate<? super JavaClass> canDependPackageFindByPackageName(
      String prefixPackageName, String allowPackageName) {
    return new DescribedPredicate<>(allowPackageName + "のみ利用可能") {
      @Override
      public boolean test(JavaClass input) {
        String packageName = input.getPackageName();
        if (!packageName.startsWith(prefixPackageName)) {
          return true;
        }
        return packageName.equals(allowPackageName);
      }
    };
  }

  /**
   * StringUtilsなど複数のライブラリで同一名称のクラスが存在する場合の依存先を1つのパッケージに限定するために利用する。
   * 指定したクラス名のパッケージが許可されたパッケージであることを確認する
   *
   * @param className パッケージ名の判定対象としたいクラス名
   * @param allowPackageName 利用を許可するパッケージ名
   * @return
   */
  private static DescribedPredicate<? super JavaClass> canDependPackageFindByClassName(
      String className, String allowPackageName) {
    return new DescribedPredicate<>(allowPackageName + "." + className + "のみ利用可能") {
      @Override
      public boolean test(JavaClass input) {
        String simpleName = input.getSimpleName();
        String packageName = input.getPackageName();

        if (!simpleName.equals(className)) {
          return true;
        }
        return packageName.equals(allowPackageName);
      }
    };
  }
}
