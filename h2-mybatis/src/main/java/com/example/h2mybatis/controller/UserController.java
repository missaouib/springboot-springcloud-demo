package com.example.h2mybatis.controller;

import com.example.h2mybatis.model.User;
import com.example.h2mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/get")
    public User getUser(@RequestBody User user) {
        User result = userService.getUser(user);
        return result;
    }
}
