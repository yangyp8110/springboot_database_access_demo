package com.demo.gener.utils.annotation;

import java.lang.annotation.*;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface IgnoreResponseAdvice {
}