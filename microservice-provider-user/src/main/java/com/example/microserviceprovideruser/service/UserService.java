package com.example.microserviceprovideruser.service;

import com.example.microserviceprovideruser.mapper.UserMapper;
import com.example.microserviceprovideruser.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findById(Long id) {
        return userMapper.findById(id);
    }
}
