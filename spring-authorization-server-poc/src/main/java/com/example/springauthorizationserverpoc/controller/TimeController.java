package com.example.springauthorizationserverpoc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("time")
public class TimeController {
  @PreAuthorize("hasAuthority('SCOPE_time.read')")
  @GetMapping
  public String index(Principal principal) {
    System.out.println(principal);
    // 既存の仕組みを使うからOK
    // User user = (User)SecurityContextHolder.getContext().getAuthentication().getCredentials();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime localTime = LocalTime.now();
    return dateTimeFormatter.format(localTime);
  }
}
