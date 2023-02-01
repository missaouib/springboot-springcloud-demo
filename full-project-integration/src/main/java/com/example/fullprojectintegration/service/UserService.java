package com.example.fullprojectintegration.service;

import com.example.fullprojectintegration.mapper.UserMapper;
import com.example.fullprojectintegration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public User getUser(User user) {
        return userMapper.getUser(user);
    }
}
