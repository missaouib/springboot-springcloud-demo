package com.example.fullprojectintegration.service;

import com.example.fullprojectintegration.mapper.UserMapper;
import com.example.fullprojectintegration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    //@Autowired
    private UserMapper userMapper;

    //@Transactional
    public User getUser(User user) {
        return new User();
    }
}
