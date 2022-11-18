package com.example.springjwttutorial.infrastructure.dto.user;

import com.example.springjwttutorial.domain.model.user.Coin;
import com.example.springjwttutorial.domain.model.user.User;

public class UserEntity {
  private int id;
  private String name;
  private String password;
  private int coin;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getCoin() {
    return coin;
  }

  public void setCoin(int coin) {
    this.coin = coin;
  }

  public User toUser() {
    return new User(id, name, new Coin(coin));
  }
}
