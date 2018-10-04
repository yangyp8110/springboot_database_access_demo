package com.demo.gener.utils;

/**
 * @author mr.yang.demo
 * @Description:
 * @date 2018/9/18
 */
public enum ReturnCode {
    UNKNOWN_ERROR(9999, "系统出错了");

    private Integer errorCode;
    private String errorMsg;

    ReturnCode(Integer code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }
}
