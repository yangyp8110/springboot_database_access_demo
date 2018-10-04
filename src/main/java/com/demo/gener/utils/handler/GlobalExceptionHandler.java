package com.demo.gener.utils.handler;

import com.demo.gener.utils.exception.MyException;
import com.demo.gener.utils.ApiResponse;
import com.demo.gener.utils.annotation.IgnoreResponseAdvice;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Slf4j
@Order(2)
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseBody
    @IgnoreResponseAdvice
    public ApiResponse processUnauthenticatedException(HttpServletRequest request, MyException ex) {
        ApiResponse<Object> retVal = new ApiResponse<>();
        log.info(String.format("统一异常处理。url:%s,code:%s,message:%s,content:%s,errorMsg:%s", request.getRequestURI(), ex.getCode(), ex.getTips(), ex.getContent(), ex.getMessage()));
        retVal.setCode(ex.getCode());
        retVal.setRet(ex.getContent() == null ? "" : ex.getContent());
        retVal.setMessage(ex.getTips());
        return retVal;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @IgnoreResponseAdvice
    public ApiResponse unExpectedException(HttpServletRequest request, Exception ex) {
        ApiResponse<Object> retVal = new ApiResponse<>();
        log.info("未知异常处理。url:" + request.getRequestURI(), ex);
        retVal.setCode(999999);
        retVal.setMessage("未知系统异常！");
        retVal.setRet(null);
        return retVal;
    }
}