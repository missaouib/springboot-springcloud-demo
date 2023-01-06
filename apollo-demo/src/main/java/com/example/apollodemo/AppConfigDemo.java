package com.example.apollodemo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.Collections;

/**
   VM options：
   -Dapp.id=apollo-quickstart -Denv=DEV -Dapollo.meta=http://192.168.3.57:8081
 */
public class AppConfigDemo {

    public static void main(String[] args) throws Exception {
        Config config = ConfigService.getConfig("application");
        String someKey = "sms.enable";

        while (true) {
            String value = config.getProperty(someKey,null);
            System.out.println("sms enable: "+value);

            Thread.sleep(3000L);
        }
    }

}
