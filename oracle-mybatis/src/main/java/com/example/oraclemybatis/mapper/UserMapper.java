package com.example.oraclemybatis.mapper;


import com.example.oraclemybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    User getUser(User user);
}
