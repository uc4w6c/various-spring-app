package com.example.springjwttutorial.domain.model.gacha;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springjwttutorial.domain.model.character.Character;

public record Gacha(int needCoind, List<Character> characters) {
  public Gacha(List<Character> characters) {
    this(300, characters);
  }

  // ランダムに登場キャラクターから3体を取得する
  public List<Character> play(){
    Collections.shuffle(this.characters);
    return this.characters.stream()
        .limit(3)
        .collect(Collectors.toList());
  }
}
