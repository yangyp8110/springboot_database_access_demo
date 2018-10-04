package com.demo.gener.utils.handler;

import com.demo.gener.utils.ApiResponse;
import com.demo.gener.utils.annotation.IgnoreResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Order(1)
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return !methodParameter.hasMethodAnnotation(IgnoreResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("success");
        apiResponse.setRet(o);
        return apiResponse;
    }
}