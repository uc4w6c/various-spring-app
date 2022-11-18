package com.example.springjwttutorial.application.usecase.gacha.impl;

import com.example.springjwttutorial.application.usecase.gacha.GachaUseCase;
import com.example.springjwttutorial.domain.model.gacha.Gacha;
import com.example.springjwttutorial.domain.model.user.User;
import com.example.springjwttutorial.domain.repository.character.CharacterRepository;
import com.example.springjwttutorial.domain.model.character.Character;
import com.example.springjwttutorial.domain.repository.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GachaUseCaseImpl implements GachaUseCase {
  private UserRepository userRepository;

  private CharacterRepository characterRepository;


  public GachaUseCaseImpl(UserRepository userRepository, CharacterRepository characterRepository) {
    this.userRepository = userRepository;
    this.characterRepository = characterRepository;
  }

  @Override
  public List<Character> getCharacters(){
    // ユーザーの取得
    User user = this.userRepository.findByName("zenn").toUser();
    // キャラクターの取得
    List<Character> characters =
        this.characterRepository.getCharacters()
            .stream().map(
                i -> new Character(i.getId(), i.getName(), i.getLank())
            ).collect(Collectors.toList());
    // ガチャガチャの作成
    Gacha gacha = new Gacha(characters);
    // コインの利用
    user.useCoin(gacha.needCoind());
    // ガチャの利用（キャラクターの取得）
    List<Character> characterList = gacha.play();
    // TODO ユーザー情報の更新

    return characterList;
  }
}
