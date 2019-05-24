package com.example.springboot.manager;

import com.example.springboot.model.User;

import java.util.List;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 16:41
 **/
public interface IUserManager {
    List<User> queryAll();
}
