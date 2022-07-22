package com.forum.projectlzy.controller;

import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * @author 扬
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/regist")
    public ResultDto register(String username,
                              String password,
                              String email) {
        password = User.decryptPassword(password);
        return userService.regist(username, password, email);
    }

}
