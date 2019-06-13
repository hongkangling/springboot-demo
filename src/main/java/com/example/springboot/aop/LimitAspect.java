package com.example.springboot.aop;

import com.dangdang.ddframe.job.util.env.IpUtils;
import com.example.springboot.annotation.RateLimit;
import com.example.springboot.utils.IpAdrressUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-06-12 10:57
 **/
@Slf4j
@Configuration
@Aspect
@Order(-1)
public class LimitAspect {

    @Autowired
    private RedisTemplate<String, Serializable>  redisTemplate;
    @Autowired
    private DefaultRedisScript<Number> redisScript;

    @Pointcut("execution(* com.example.springboot.web..*(..))||execution(* com.example.springboot.limit.api..*(..))")
    public void interceptor() {

    }

    @AfterReturning("interceptor()")
    public void doAfterReturn(JoinPoint jp) {
        System.out.println("AfterReturn");
    }

    @After("interceptor()")
    public void doAfter(JoinPoint jp) {
        System.out.println("After");
    }

    @Before("interceptor()")
    public void doBefore(JoinPoint jp) {
        System.out.println("Before");
    }

    @AfterThrowing("interceptor()")
    public void doAfterThrowing() {
        System.out.println("AfterThrowing");
    }

    @Around("interceptor()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();

        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        Class<?> target = method.getDeclaringClass();

        if(rateLimit!=null){
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String ipAdrress = IpAdrressUtil.getIpAdrress(request);
//            if("0.")
            String key=ipAdrress+"_"+target.getName()+ "_"+method.getName()+ "_"+rateLimit.key();
            List<String> list = Collections.singletonList(key);
            Number execute = redisTemplate.execute(redisScript, list, rateLimit.count(), rateLimit.time());

            if(null!=execute&&execute.intValue()!=0&&execute.intValue()<=rateLimit.count()){
                log.info("限流时间段内访问第：{} 次", execute.toString());
                return joinPoint.proceed();
            }
        }else{
            return joinPoint.proceed();
        }
        log.error(" 已经达到限流次数",rateLimit.count());
        throw new RuntimeException("达到限流次数");
    }
}
