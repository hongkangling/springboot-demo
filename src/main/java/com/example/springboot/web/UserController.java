package com.example.springboot.web;

import com.example.springboot.common.ResultJson;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-03 14:21
 **/
@Slf4j
@RestController
@Api(value ="用户操作类",description = "这是用户信息查询类")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResultJson list(@PathVariable Long id) {
        log.info("测试日志");
        return ResultJson.success("success",userService.findById(id));
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResultJson queryAll(){
        return ResultJson.success(userService.queryAll());
    }
}
