package com.example.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author linghongkang
 * @description: 限流读取Lua  脚本
 * @create: 2019-06-12 10:18
 **/
@Component
public class CommonLimit {

    /**
     *
     * @return
     */
    @Bean
    public DefaultRedisScript<Number>  readRedisScript(){
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
//        ClassPathResource pathResource = new ClassPathResource("lua/limitIp.lua");
//        boolean exists = pathResource.exists();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/limitIp.lua")));

        redisScript.setResultType(Number.class);

        return redisScript;
    }

    /**
     * 获取redisTemplate
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable>  limitRedisTemplate(LettuceConnectionFactory connectionFactory){

        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);

        return redisTemplate;
    }
}
