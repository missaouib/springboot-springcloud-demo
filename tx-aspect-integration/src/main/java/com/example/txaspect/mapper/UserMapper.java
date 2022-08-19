package com.example.txaspect.mapper;

import com.example.txaspect.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    User getUser(User user);
}
