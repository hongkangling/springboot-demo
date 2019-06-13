package com.example.springboot.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface RateLimit {

    /**
     * 限流key
     * @return
     */
    String key() default "";

    /**
     * 限流时间
     * @return
     */
    int time();

    /**
     * 限流次数
     * @return
     */
    int count();
}
