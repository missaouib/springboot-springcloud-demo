package com.example.oraclemybatis.controller;

import com.example.oraclemybatis.model.User;
import com.example.oraclemybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/user/get")
    public User getUser(@RequestBody User user) {
        User result = userService.getUser(user);
        return result;
    }
}
