package com.example.oome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HelloController {
  private List<byte[]> list = new ArrayList<>();

  @GetMapping
  public String index() {
    list.add(new byte[1024 * 1024 * 100]); // 100MBの配列を生成する
    return "hello";
  }
}
