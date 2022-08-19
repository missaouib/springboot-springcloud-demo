package com.example.springbootspringclouddemo;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SpringCloudApplication
public class SpringbootSpringcloudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpringcloudDemoApplication.class, args);
        String json = "{\"name\": \"zhangsan\"}";
    }


}
