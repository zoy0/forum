package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 表中用户id
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 用户创建时间
     */
    private Date createdTime;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户状态
     * 0表示注销状态, 1表示正常使用, 2表示封禁状态
     */
    private Integer state;

    /**
     * 1 表示管理员, 0 表示普通用户
     */
    private Integer admin;

}
