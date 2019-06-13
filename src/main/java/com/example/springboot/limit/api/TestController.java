package com.example.springboot.limit.api;

import com.example.springboot.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-06-12 11:32
 **/
@RestController
@Slf4j
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RateLimit(key="test",time = 5,count=1)
    @GetMapping("/test/limit")
    public String testLimit(){
        RedisAtomicInteger rediscounter = new RedisAtomicInteger("rediscounter", redisTemplate.getConnectionFactory());
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        log.info(format+"累计访问次数："+rediscounter.getAndIncrement());
        return format+"累计访问次数："+rediscounter.getAndIncrement();
    }
}
