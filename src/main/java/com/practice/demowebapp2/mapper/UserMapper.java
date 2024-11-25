package com.practice.demowebapp2.mapper;

import com.practice.demowebapp2.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUserId(String userId);
    void save(User user);
}