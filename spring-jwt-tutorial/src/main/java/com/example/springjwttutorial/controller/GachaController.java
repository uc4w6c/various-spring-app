package com.example.springjwttutorial.controller;

import com.example.springjwttutorial.application.usecase.gacha.GachaUseCase;
import com.example.springjwttutorial.domain.model.character.Character;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GachaController {
  private GachaUseCase gachaUseCase;

  public GachaController(GachaUseCase gachaUseCase) {
    this.gachaUseCase = gachaUseCase;
  }

  @PostMapping("api/playgacha")
  public List<CharacterOut> play() {
    List<Character> characters = this.gachaUseCase.getCharacters();
    List<CharacterOut> characterOuts = characters.stream()
        .map(CharacterMapper::toOut)
        .collect(Collectors.toList());
    return characterOuts;
  }

  public record CharacterOut(String name) {
    public static CharacterOut by(String name) {
      return new CharacterOut(name);
    }
  }

  public static class CharacterMapper {
    static CharacterOut toOut(Character character){
      return CharacterOut.by(character.name());
    }
  }
}
