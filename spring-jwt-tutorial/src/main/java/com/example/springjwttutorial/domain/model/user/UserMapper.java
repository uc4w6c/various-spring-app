package com.example.springjwttutorial.domain.model.user;

import com.example.springjwttutorial.domain.dto.user.UserEntity;

public interface UserMapper {
  UserEntity toEntity(User user);
}
