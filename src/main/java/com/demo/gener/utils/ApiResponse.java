package com.demo.gener.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Getter
@Setter
public class ApiResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T ret;
}