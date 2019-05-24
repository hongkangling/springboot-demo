package com.example.springboot.service.impl;

import com.example.springboot.mapper.UserMapper;
import com.example.springboot.model.User;
import com.example.springboot.model.example.UserExample;
import com.example.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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
    @Autowired
    private ApplicationContext applicationContext;

    private  UserServiceImpl bean;
    @PostConstruct
    public  void init(){
         bean = applicationContext.getBean(UserServiceImpl.class);
    }
    @Override
    public User findById(Long id){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(1L);
        List<User> userList = userMapper.selectByExample(userExample);
//        ((UserService) AopContext.currentProxy()).testAsync();
        List<User> users = bean.queryAll();
        bean.testAsync(users);
        log.info("total is "+userList.size());
        return userList.get(0);
    }

    @Override
    public List<User> queryAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    @Async
    public void  testAsync( List<User>  users) {
        System.out.println("异步调用开始。。。"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println("异步调用end。。。"+Thread.currentThread().getName());

    }

}
