package com.practice.demowebapp2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.demowebapp2.mapper.UserMapper;
import com.practice.demowebapp2.bean.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean registerUser(User user) {
        if (userMapper.findByUserId(user.getUserId()) != null) {
            return false;
        }
        userMapper.save(user);
        return true;
    }

    public boolean authenticateUser(String userId, String password) {
        User user = userMapper.findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }

    public boolean isIdAvailable(String userId) {
        return userMapper.findByUserId(userId) == null;
    }
}