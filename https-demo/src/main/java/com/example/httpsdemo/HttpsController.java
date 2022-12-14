package com.example.httpsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpsController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello HTTPS";
    }

}