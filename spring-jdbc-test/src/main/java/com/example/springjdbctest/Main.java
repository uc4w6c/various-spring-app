package com.example.springjdbctest;

import java.sql.*;

public class Main {
  public static void main(String[] args) {
    String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "password";

    ResultSet resultSet = null;

    try (Connection connection = DriverManager.getConnection(connectionUrl, user, password);
      Statement statement = connection.createStatement();) {

      String selectSql = "SELECT * from books";
      resultSet = statement.executeQuery(selectSql);

      while (resultSet.next()) {
        System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
