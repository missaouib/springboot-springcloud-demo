package com.example.oraclemybatis.service;

import com.example.oraclemybatis.mapper.UserMapper;
import com.example.oraclemybatis.model.User;
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
