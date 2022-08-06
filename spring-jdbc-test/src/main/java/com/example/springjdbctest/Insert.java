package com.example.springjdbctest;

import java.sql.*;

public class Insert {
  public static void main(String[] args) {
    String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "password";

    final String sql1 = "insert into books values (2, 'Effective');";
    final String sql2 = "insert into books values (2, 'デザインパターン');";

    ResultSet resultSet = null;

    try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
      connection.setAutoCommit(false);
      try (PreparedStatement statement = connection.prepareStatement(sql1)) {
        int rows = statement.executeUpdate();
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
      try (PreparedStatement statement = connection.prepareStatement(sql2)) {
        int rows = statement.executeUpdate();
        throw new SQLException();
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
