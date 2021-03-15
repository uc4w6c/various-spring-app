# Spring の Flyaway動作を調べるためのプロジェクト

## 
BeanPropertyRowMapper
でマッピングするときはデフォルトコンストラクタとsetterが必要みたい
https://loglog.xyz/programming/java/jdbctemplate_query_select

schema.sql, data.sqlは以下から呼び出しだと思われる
DataSourceAutoConfiguration
 -> 設定読み込みっぽい
DataSourceInitializationConfiguration
DataSourceInitializer
 -> 実行か？

## 25613
https://github.com/spring-projects/spring-boot/issues/25613
を直す場合
DataSourceInitializer.getResources
を修正

直し方
The specified resource  data.sql does not exist.
Resource 'data.sql' does not exist

