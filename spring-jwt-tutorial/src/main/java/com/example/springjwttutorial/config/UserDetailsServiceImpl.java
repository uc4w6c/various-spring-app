package com.example.springjwttutorial.config;

import com.example.springjwttutorial.domain.repository.user.UserRepository;
import com.example.springjwttutorial.infrastructure.dto.user.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try{
      UserEntity entity = userRepository.findByName(username);
      // 認可があればここで設定できる
      // org.springframework.security.core.userdetails.Userにして返却する
      // パスワードエンコーダを利用してパスワードはエンコードをかける
      return new User(entity.getName(), PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(entity.getPassword()), new ArrayList<>());
    }catch (Exception e) {
      throw new UsernameNotFoundException("ユーザーが見つかりません");
    }
  }
}
