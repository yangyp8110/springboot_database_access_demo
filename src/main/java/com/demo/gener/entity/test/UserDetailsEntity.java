package com.demo.gener.entity.test;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Getter
@Setter
public class UserDetailsEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户描述
     */
    private String userDesc;
    /**
     * 新增时间
     */
    private java.util.Date inserttime;
    /**
     * 修改时间
     */
    private java.util.Date updatetime;
}