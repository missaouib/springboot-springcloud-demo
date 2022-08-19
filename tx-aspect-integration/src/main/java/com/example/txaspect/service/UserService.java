package com.example.txaspect.service;

import com.example.txaspect.mapper.UserMapper;
import com.example.txaspect.model.User;
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
