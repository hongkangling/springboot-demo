package com.example.springboot.service.impl;

import com.example.springboot.mapper.UserMapper;
import com.example.springboot.model.User;

import com.example.springboot.model.example.UserExample;
import com.example.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-03 13:59
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Long id) {
        log.info("测试");
        UserExample userExample = new UserExample();
        return userMapper.selectByExample(userExample).get(0);
    }
}
