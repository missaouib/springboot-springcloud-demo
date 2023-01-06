package com.example.apollodemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApolloController {

    @Value("${sms.enable:false}")
    private boolean smsEnable;

    @RequestMapping("/apollo/config")
    public String apolloConfig() {
        return Boolean.valueOf(smsEnable).toString();
    }

}
