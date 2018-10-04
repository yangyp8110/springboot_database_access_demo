package com.demo.gener.utils.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Getter
@Setter
public class MyException extends RuntimeException {
    private Integer code;
    private String tips;
    private Object content;

    public MyException(Integer code, String tips, Object object, String error, Throwable throwable) {
        super(error, throwable);
        this.code = code;
        this.tips = tips;
        this.content = object;
    }

    public MyException(Integer code, String tips, Object object, String error) {
        super(error);
        this.code = code;
        this.tips = tips;
        this.content = object;
    }

    public MyException(Integer code, String tips, Object object) {
        super();
        this.code = code;
        this.tips = tips;
        this.content = object;
    }

    public MyException(Integer code, String tips, String error) {
        super(error);
        this.code = code;
        this.tips = tips;
    }

    public MyException(Integer code, String tips) {
        this(code, tips, null);
    }
}