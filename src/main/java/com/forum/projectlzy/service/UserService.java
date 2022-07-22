package com.forum.projectlzy.service;

import com.forum.projectlzy.entity.ResultDto;

/**
 * 用户业务层
 *
 * @author 扬
 */
public interface UserService {

    /**
     * 注册账号
     *
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱地址
     * @return
     */
    ResultDto regist(String username, String password, String email);
}
