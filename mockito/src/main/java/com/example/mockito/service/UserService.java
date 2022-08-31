package com.example.mockito.service;


import com.example.mockito.mapper.UserMapper;
import com.example.mockito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopService shopService;

    @Transactional
    public User getUser(User user) {
        User result = userMapper.getUser(user);
        getShopService().shop();
        return result;
    }

    public ShopService getShopService() {
        return shopService;
    }
}
