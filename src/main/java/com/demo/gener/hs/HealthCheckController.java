package com.demo.gener.hs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.gener.utils.annotation.IgnoreResponseAdvice;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@RestController
@RequestMapping("/")
public class HealthCheckController {
    @IgnoreResponseAdvice
    @RequestMapping("/hs")
    public String health(){
        return "OK";
    }
}