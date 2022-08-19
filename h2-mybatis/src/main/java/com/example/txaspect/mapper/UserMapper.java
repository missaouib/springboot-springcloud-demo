package com.example.h2mybatis.mapper;

import com.example.h2mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    User getUser(User user);
}
