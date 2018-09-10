package com.example.microservicesimpleprovideruser.service;

import com.example.microservicesimpleprovideruser.mapper.UserMapper;
import com.example.microservicesimpleprovideruser.po.User;
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
