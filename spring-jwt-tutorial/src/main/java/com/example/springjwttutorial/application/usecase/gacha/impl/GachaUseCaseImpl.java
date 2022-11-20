package com.example.springjwttutorial.application.usecase.gacha.impl;

import com.example.springjwttutorial.application.usecase.gacha.GachaUseCase;
import com.example.springjwttutorial.domain.dto.stock.StockEntity;
import com.example.springjwttutorial.domain.dto.user.UserEntity;
import com.example.springjwttutorial.domain.model.gacha.Gacha;
import com.example.springjwttutorial.domain.model.user.User;
import com.example.springjwttutorial.domain.model.user.UserMapper;
import com.example.springjwttutorial.domain.repository.character.CharacterRepository;
import com.example.springjwttutorial.domain.model.character.Character;
import com.example.springjwttutorial.domain.repository.stock.StockRepository;
import com.example.springjwttutorial.domain.repository.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GachaUseCaseImpl implements GachaUseCase {
  private UserRepository userRepository;
  private CharacterRepository characterRepository;
  private UserMapper userMapper;
  private StockRepository stockRepository;


  public GachaUseCaseImpl(UserRepository userRepository, CharacterRepository characterRepository, UserMapper userMapper, StockRepository stockRepositor) {
    this.userRepository = userRepository;
    this.characterRepository = characterRepository;
    this.userMapper = userMapper;
    this.stockRepository = stockRepositor;
  }

  @Override
  @Transactional
  public List<Character> getCharacters() {
    // ユーザー名を取得する
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    // ユーザーの取得
    User user = this.userRepository.findByName(username).toUser();
    // キャラクターの取得
    List<Character> characters =
        this.characterRepository.getCharacters()
            .stream().map(
                i -> new Character(i.getId(), i.getName(), i.getLank())
            ).collect(Collectors.toList());
    // ガチャガチャの作成
    Gacha gacha = new Gacha(characters);
    // コインの利用
    User afterUsr = user.useCoin(gacha.needCoind());
    // ガチャの利用（キャラクターの取得）
    List<Character> characterList = gacha.play();
    UserEntity afterUserEntity = this.userMapper.toEntity(afterUsr);
    this.userRepository.updateCoin(afterUserEntity.getName(), afterUserEntity.getCoin());

    List<StockEntity> stockEntities = characterList.stream().map(i -> {
      return new StockEntity(user.id(), i.id());
    }).toList();

    this.stockRepository.save(stockEntities);

    return characterList;
  }
}
