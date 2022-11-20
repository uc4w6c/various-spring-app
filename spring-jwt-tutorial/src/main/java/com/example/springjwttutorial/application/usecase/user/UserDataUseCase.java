package com.example.springjwttutorial.application.usecase.user;

import java.util.List;
import com.example.springjwttutorial.domain.model.character.Character;

public interface UserDataUseCase {
  List<Character> getPossessionList();
}
