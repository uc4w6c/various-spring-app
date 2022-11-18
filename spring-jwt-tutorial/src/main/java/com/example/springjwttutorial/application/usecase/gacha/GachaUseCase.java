package com.example.springjwttutorial.application.usecase.gacha;

import java.util.List;
import com.example.springjwttutorial.domain.model.character.Character;

public interface GachaUseCase {
  List<Character> getCharacters();
}
