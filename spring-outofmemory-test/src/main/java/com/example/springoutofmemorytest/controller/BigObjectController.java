package com.example.springoutofmemorytest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bigobject")
public class BigObjectController {
  private BigObject bigObject;

  @GetMapping
  public String index(@RequestParam("size") int size) {
    this.bigObject = new BigObject(size);
    System.out.println("done (" + size + ")");
    return "end";
  }

  public static class BigObject {
    public byte[][][] data;

    public BigObject(int size) {
      this.data = new byte[1024][1024][size];
    }
  }
}
