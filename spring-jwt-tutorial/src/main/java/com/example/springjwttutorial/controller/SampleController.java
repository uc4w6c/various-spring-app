package com.example.springjwttutorial.controller;

import com.example.springjwttutorial.domain.model.user.User;
import com.example.springjwttutorial.domain.repository.user.UserRepository;
import com.example.springjwttutorial.domain.dto.user.UserEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {
  private UserRepository userRepository;

  public SampleController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/api/test")
  public String test(){
    return "認証が成功しています";
  }

  @PostMapping("/sample")
  @CrossOrigin
  public SampleResponse post(@RequestBody SampleRequest sampleRequest) {
    int id = sampleRequest.id();
    var sampleResponse = new SampleResponse("zenn", id);
    return sampleResponse;
  }

  @GetMapping("/test")
  public User get(){
    UserEntity userEntity = this.userRepository.findByName("zenn");
    return userEntity.toUser();
  }
}
