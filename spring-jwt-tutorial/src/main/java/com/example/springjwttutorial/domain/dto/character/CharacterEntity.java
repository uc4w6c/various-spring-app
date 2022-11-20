package com.example.springjwttutorial.domain.dto.character;

import com.example.springjwttutorial.domain.model.character.Character;

public class CharacterEntity {
  private int id;
  private String name;
  private int lank;

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

  public int getLank() {
    return lank;
  }

  public void setLank(int lank) {
    this.lank = lank;
  }

  public Character toCharacter(){
    return new Character(id, name, lank);
  }
}
