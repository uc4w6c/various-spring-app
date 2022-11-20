package com.example.springjwttutorial.domain.dto.stock;

public class StockEntity {
  private int user_id;
  private int character_id;

  public StockEntity(int user_id, int character_id) {
    this.user_id = user_id;
    this.character_id = character_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public int getCharacter_id() {
    return character_id;
  }

  public void setCharacter_id(int character_id) {
    this.character_id = character_id;
  }
}
