package com.example.dockerfiledemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        List<String> list = Arrays.asList("hello");
        return list.stream().map(e->e.concat(" word!!!")).collect(Collectors.joining());
    }

}
