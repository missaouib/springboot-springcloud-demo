package com.example.deadlock.service;

import com.example.deadlock.mapper.UserMapper;
import com.example.deadlock.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Transactional
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

    public Map<String,Object> getDeadLock() {
        return userMapper.getDeadLock();
    }


    public void mockDeadLock() {
        new Thread() {
            @Override
            public void run() {
                userService.tx1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                userService.tx2();
            }
        }.start();
    }

    @Transactional
    public void tx1() {
        try {
            User user = userMapper.getUser(new User());
            System.out.println(user);
            int update1 = userMapper.update1();
            System.out.println("update1:"+update1);
            Thread.sleep(200L);
            int update3 = userMapper.update3();
            System.out.println("update3:"+update3);
        } catch (Exception e) {
            Map<String, Object> map = getDeadLock();
            System.out.println("tx1==============:"+map.get("Status"));
            e.printStackTrace();
        }
    }

    @Transactional
    public void tx2() {
        try {
            Thread.sleep(100L);
            int update3 = userMapper.update3();
            System.out.println("update3:"+update3);
            Thread.sleep(300L);
            int update1 = userMapper.update1();
            System.out.println("update1:"+update1);
        } catch (Exception e) {
            Map<String, Object> map = getDeadLock();
            System.out.println("tx2==============:"+map.get("Status"));
            e.printStackTrace();
        }
    }
}
