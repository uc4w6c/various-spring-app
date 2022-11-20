package com.example.springjwttutorial.application.usecase.user.impl;

import com.example.springjwttutorial.application.usecase.user.UserDataUseCase;
import com.example.springjwttutorial.domain.model.user.User;
import com.example.springjwttutorial.domain.repository.stock.StockRepository;
import com.example.springjwttutorial.domain.repository.user.UserRepository;
import com.example.springjwttutorial.domain.model.character.Character;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataUseCaseImpl implements UserDataUseCase {
  private UserRepository userRepository;
  private StockRepository stockRepository;

  public UserDataUseCaseImpl(UserRepository userRepository, StockRepository stockRepository) {
    this.userRepository = userRepository;
    this.stockRepository = stockRepository;
  }
  @Override
  public List<Character> getPossessionList() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = this.userRepository.findByName(username).toUser();

    return this.stockRepository.getUserCharacters(user.id())
        .stream().map(
            i -> new Character(i.getId(), i.getName(), i.getLank())
        ).toList();
  }
}
