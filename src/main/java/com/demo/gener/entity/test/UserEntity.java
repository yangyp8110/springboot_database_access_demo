package com.demo.gener.entity.test;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Getter
@Setter
public class UserEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 插入时间
     */
    private java.util.Date inserttime;
    /**
     * 更新时间
     */
    private java.util.Date updatetime;
}