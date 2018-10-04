package com.demo.gener.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}