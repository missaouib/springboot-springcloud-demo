package com.example.mockito.mapper;

import com.example.mockito.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    User getUser(User user);
}
