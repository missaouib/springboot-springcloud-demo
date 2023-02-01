package com.example.fullprojectintegration.controller;

import com.example.fullprojectintegration.model.User;
import com.example.fullprojectintegration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
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
        LOGGER.info("logFile:{}","ss");
        LOGGER.info("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        LOGGER.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return result;
    }

    public void createPrototype() {
        User user1 = userService.getUser(new User());
        User user2 = userService.getUser(new User());

        System.out.println(user1);

    }
}
