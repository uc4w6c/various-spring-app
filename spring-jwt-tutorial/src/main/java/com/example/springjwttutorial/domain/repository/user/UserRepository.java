package com.example.springjwttutorial.domain.repository.user;

import com.example.springjwttutorial.domain.dto.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
  UserEntity findByName(String name);
  void updateCoin(String name, int coin);
}
