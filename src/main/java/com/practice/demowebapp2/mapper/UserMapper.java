package com.practice.demowebapp2.mapper;

import com.practice.demowebapp2.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUserId(String userId);
    void save(User user);
    List<User> findAll();
}