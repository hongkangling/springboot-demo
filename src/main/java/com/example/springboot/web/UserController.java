package com.example.springboot.web;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-03 14:21
 **/
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public User list(@PathVariable Long id) {
        log.info("{} is 测试日志", id);
        return userService.findById(id);
    }
}
