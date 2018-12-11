package com.example.springboot.enums;

import lombok.Data;


public enum ReturnCodeEnum {
    /**
     * 1 接口请求成功
     */
    SUCCESS(1,"success"),
    /**
     * 0 接口请求失败
     */
    FAIL(0,"fail"),
    /**
     *  业务逻辑执行出错
     */
    OTHER(30001,"业务异常");

    private int  code;
    private String msg;

    ReturnCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
