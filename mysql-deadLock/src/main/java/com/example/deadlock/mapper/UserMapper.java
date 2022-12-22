package com.example.deadlock.mapper;

import com.example.deadlock.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {


    User getUser(User user);

    Map<String,Object> getDeadLock();

    int update1(@Param("username") String username);

    int update3(@Param("username") String username);
}
