package com.example.springjwttutorial.controller.userdata;

import com.example.springjwttutorial.application.usecase.user.UserDataUseCase;
import com.example.springjwttutorial.controller.GachaController;
import com.example.springjwttutorial.domain.model.character.Character;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDataController {
  private UserDataUseCase userDataUseCase;

  public UserDataController(UserDataUseCase userDataUseCase) {
    this.userDataUseCase = userDataUseCase;
  }

  @GetMapping("api/stock")
  public List<GachaController.CharacterOut> mylist(){
    List<Character> characters = this.userDataUseCase.getPossessionList();
    return characters
        .stream().map(GachaController.CharacterMapper::toOut)
        .toList();
  }
}
