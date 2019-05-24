package com.example.springboot.manager.impl;

import com.example.springboot.manager.IUserManager;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 16:42
 **/
@Component
public class UserManagerImpl implements IUserManager {
    @Autowired
    private UserService userService;
    @Override
    public List<User> queryAll() {
        List<User> users = userService.queryAll();
        userService.testAsync(users);
        return users;
    }
}
