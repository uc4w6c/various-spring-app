package com.java17;

import javax.validation.constraints.NotBlank;

public class User2Request {
  @NotBlank private String name;
  private int age;

  public User2Request(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "User2{" + "name='" + name + '\'' + ", age=" + age + '}';
  }
}
