package com.example.fullprojectintegration;

import com.example.fullprojectintegration.controller.UserController;
import com.example.fullprojectintegration.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FullProjectIntegrationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FullProjectIntegrationApplication.class, args);
        UserController controller = run.getBean(UserController.class);
        //controller.getUser(new User());
        controller.createPrototype();
        System.out.println(controller);
    }

}
