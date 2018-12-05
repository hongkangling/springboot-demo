package com.example.springboot.common;

import lombok.Data;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-05 15:38
 **/
@Data
public class ResultJson {
    private int  code;
    private String msg;
    private Object  data;

    public ResultJson() {
        super();
    }

    public ResultJson(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static ResultJson failResult(String msg){
        return new ResultJson(-1,msg,null);
    }
    public static ResultJson success(String msg,Object data){
        return new ResultJson(1,msg,data);
    }
    public static ResultJson success(Object data){
        return new ResultJson(1,"success",data);
    }
}
