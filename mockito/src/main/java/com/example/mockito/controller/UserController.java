package com.example.mockito.controller;


import com.example.mockito.model.User;
import com.example.mockito.service.GoodsService;
import com.example.mockito.service.ShopService;
import com.example.mockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/user/get")
    public User getUser(@RequestBody User user) {
        User result = userService.getUser(user);
        shopService.shop();
        return result;
    }
}
