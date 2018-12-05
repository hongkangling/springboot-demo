package com.example.springboot.service;

import com.example.springboot.model.User;

import java.util.List;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-03 13:57
 **/
public interface UserService {

    /**
     * 获取用户信息根据用户ID
     * @param id
     * @return
     */
    User findById(Long id);

    List<User> queryAll();


}
