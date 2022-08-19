package com.example.springbootspringclouddemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.scope.Scope;

@RestController
public class UserController {

    @RequestMapping("/user/get")
    public String getUser(@RequestBody String userId) {
        return "getUser";
    }

}
