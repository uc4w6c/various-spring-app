package com.example.springresourceserverpoc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HelloController {
  @GetMapping
  @PreAuthorize("hasAuthority('SCOPE_message.read')")
  public String index(Principal principal) {
    return principal.getName();
  }
}
