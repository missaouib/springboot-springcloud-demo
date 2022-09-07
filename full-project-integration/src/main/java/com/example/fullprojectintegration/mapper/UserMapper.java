package com.example.fullprojectintegration.mapper;

import com.example.fullprojectintegration.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    User getUser(User user);
}
