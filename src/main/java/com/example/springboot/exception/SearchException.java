package com.example.springboot.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-06 14:24
 **/
@Data
public class SearchException  {

    private String  code;
    private String msg;

}
