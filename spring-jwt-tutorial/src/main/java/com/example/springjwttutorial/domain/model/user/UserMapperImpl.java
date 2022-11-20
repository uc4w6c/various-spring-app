package com.example.springjwttutorial.domain.model.user;

import com.example.springjwttutorial.domain.dto.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
  @Override
  public UserEntity toEntity(User user) {
    UserEntity entity = new UserEntity();
    entity.setId(user.id());
    entity.setName(user.name());
    entity.setCoin(user.getCoin());
    return entity;
  }
}
