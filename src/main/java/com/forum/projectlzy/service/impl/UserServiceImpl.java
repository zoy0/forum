package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.UserDao;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.UserService;
import com.forum.projectlzy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultDto regist(String username, String password, String email) {
        if (userDao.findByUserName(username).size() != 0) {
            return ResultDto.buildSuccessResultDto("创建失败, 用户名已存在", false);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("userName", username);
            map.put("passWord", User.encryptPassword(password));
            map.put("email", email);
            map.put("createdTime", new Date());
            return userDao.insertByMap(map) == 1 ?
                    ResultDto.buildSuccessResultDto("创建成功", true) :
                    ResultDto.buildSuccessResultDto("创建失败", false);
        }
    }


    @Override
    public ResultDto login(String username, String password, boolean admin) {
        List<User> user = userDao.findByUserName(username);
        if (user.size() == 0) {
            return ResultDto.buildSuccessResultDto("用户不存在", false);
        }
        if (!user.get(0).comparePassWord(password)) {
            return ResultDto.buildSuccessResultDto("密码错误", false);
        }
        if (user.get(0).getState() != 1) {
            return ResultDto.buildSuccessResultDto("该账号已被封禁", false);
        }
        if (admin && user.get(0).getAdmin() != 1) {
            return ResultDto.buildSuccessResultDto("您不是管理员", false);
        }
        return ResultDto.buildSuccessResultDto("登录成功", JwtUtil.build(user.get(0)));

    }
}
