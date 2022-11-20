package com.example.springjwttutorial.domain.repository.user;

import com.example.springjwttutorial.domain.dto.user.UserEntity;
import com.example.springjwttutorial.domain.model.user.User;
import com.example.springjwttutorial.domain.model.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserMapper userMapper;

  @Test
  public void test_findByName(){
    UserEntity user = this.userRepository.findByName("zenn");
    assertEquals("zenn",user.getName());
  }

  @Test
  public void test_update(){
    UserEntity userEntity = this.userRepository.findByName("zenn");
    assertEquals(900, userEntity.getCoin());
    User user = userEntity.toUser();
    User afterUser = user.useCoin(300);

    // マッパーの利用
    UserEntity afterEntity = this.userMapper.toEntity(afterUser);
    // ここを実装する
    this.userRepository.updateCoin(afterEntity.getName(), afterEntity.getCoin());

    UserEntity afterUserEntity = this.userRepository.findByName("zenn");
    assertEquals(600, afterUserEntity.getCoin());
  }
}
