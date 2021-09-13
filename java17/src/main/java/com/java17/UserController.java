package com.java17;

import javax.validation.Valid;

import com.java17.response.UserResponse;
import com.java17.response.UserResponse2;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("/")
    public UserResponse register(@RequestBody @Valid User user) {
        System.out.println(user);
        System.out.println(user.name() == null);
        return new UserResponse(user.name(), true, true);
    }

    @PostMapping("/test")
    public UserResponse2 test(@RequestBody @Valid User2 user) {
        System.out.println(user);
        System.out.println(user.getName() == null);
        return new UserResponse2(user.getName(), true, true);
    }
}
