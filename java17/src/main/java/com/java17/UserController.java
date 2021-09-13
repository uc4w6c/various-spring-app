package com.java17;

import javax.validation.Valid;

import com.java17.core.User;
import com.java17.core.User2;
import com.java17.core.UserAnnotation;
import com.java17.response.UserResponse;
import com.java17.response.UserResponse2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController<DefaultHandlerExceptionResolver> {
    @PostMapping("/")
    public UserResponse register(@RequestBody @Valid UserRequest userRequest) {
        System.out.println(userRequest);
        System.out.println(userRequest.name() == null);
        return new UserResponse(userRequest.name(), true, true);
    }

    @PostMapping("/test")
    public UserResponse2 test(@RequestBody @Valid User2Request user) {
        System.out.println(user);
        System.out.println(user.getName() == null);
        return new UserResponse2(user.getName(), true, true);
    }

    @GetMapping("/")
    public String index(@UserAnnotation User user) {
        System.out.println(user);
        return "OK";
    }
}
