package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.UserDao;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultDto regist(String username, String password ,String email) {
        if(userDao.findByUserName(username).size()!=0){
            return ResultDto.buildSuccessResultDto("创建失败, 用户名已存在",false);
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("userName",username);
            map.put("passWord", User.encryptPassword(password));
            map.put("email",email);
            map.put("createdTime",new Date());
            return userDao.insertByMap(map)==1 ?
                    ResultDto.buildSuccessResultDto("创建成功",true):
                        ResultDto.buildSuccessResultDto("创建失败",false);
        }
    }
}
