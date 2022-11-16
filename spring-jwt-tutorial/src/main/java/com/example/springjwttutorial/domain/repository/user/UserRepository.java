package com.example.springjwttutorial.domain.repository.user;

import com.example.springjwttutorial.infrastructure.dto.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
  UserEntity findByName(String name);
}
