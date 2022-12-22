package com.example.deadlock.controller;

import com.example.deadlock.model.User;
import com.example.deadlock.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Value("${java.io.tmpdir:-/tmp}")
    private String logFile;

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/user/get")
    public User getUser(@RequestBody User user) {
        User result = userService.getUser(user);
        LOGGER.info("logFile:{}",logFile);
        return result;
    }

    @RequestMapping("/user/mockDeadLock")
    public Integer mockDeadLock(@RequestBody User user) {
        userService.mockDeadLock();
        return 1;
    }

    @RequestMapping("/user/getDeadLock")
    public Map<String, Object> getDeadLock(@RequestBody User user) {
        Map<String, Object> map = userService.getDeadLock();
        System.out.println(map.get("Status"));
        return map;
    }

}
