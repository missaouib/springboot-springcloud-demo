package com.example.controller;

import com.example.model.Users;
import com.example.service.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tx")
public class TestController {

    @Resource
    private UsersService usersService;

    @RequestMapping("/testRequiredAndRequired")
    public void testRequiredAndRequired(){
        Users users = new Users(100L,"hello",22,"hello@qq.com");
        usersService.InsertRequiredAndRequiredUsers(users);
    }

    @RequestMapping("/testRequiredAndNested")
    public void testRequiredAndNested(){
        Users users = new Users(100L,"hello",22,"hello@qq.com");
        usersService.InsertRequiredAndNestedUsers(users);
    }
}