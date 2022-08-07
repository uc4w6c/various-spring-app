package com.example.springjdbctest;

import java.sql.*;

public class Insert {
  public static void main(String[] args) {
    String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "password";

    final String sql1 = "insert into books values (2, 'Effective');";
    final String sql2 = "insert into books values (3, 'デザインパターン');";

    boolean isThrowException = true;

    try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
      connection.setAutoCommit(false);
      try (PreparedStatement statement1 = connection.prepareStatement(sql1);
           PreparedStatement statement2 = connection.prepareStatement(sql2)
      ) {
        statement1.executeUpdate();
        if (isThrowException) {
          throw new SQLException();
        }
        statement2.executeUpdate();
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
