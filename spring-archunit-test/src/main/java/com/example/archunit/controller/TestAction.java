package com.example.archunit.controller;

import org.springframework.transaction.annotation.Transactional;

public class TestAction {
  @Transactional
  public void test() {
    System.out.println("aa");
  }
}
