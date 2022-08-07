package com.example.springjdbctest;

import java.sql.*;

public class Insert2 {
  public static void main(String[] args) throws SQLException {
    String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "password";

    final String sql1 = "insert into books values (2, 'Effective Java');";
    final String sql2 = "insert into books values (2, 'Java言語で学ぶデザインパターン入門');";

    Connection connection = DriverManager.getConnection(connectionUrl, user, password);

    boolean isThrowException = true;
    try (connection;
         PreparedStatement statement1 = connection.prepareStatement(sql1);
         PreparedStatement statement2 = connection.prepareStatement(sql2);
    ) {
      connection.setAutoCommit(false);
      statement1.executeUpdate();
      if (isThrowException) {
        throw new SQLException();
      }
      statement2.executeUpdate();
      connection.commit();
    } catch (Exception e) {
      // ここに到達したときにはconnectionはcloseされている
      if (connection != null && !connection.isClosed()) {
        connection.rollback();
      }
      e.printStackTrace();
    }
  }
}
