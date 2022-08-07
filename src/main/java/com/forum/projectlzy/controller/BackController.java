package com.forum.projectlzy.controller;

import com.forum.projectlzy.constant.HttpCodeContant;
import com.forum.projectlzy.entity.CurrentUserInfo;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.BackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 留言回复的控制层
 */
@RestController
@RequestMapping("/back")
public class BackController {

    @Resource
    private BackService backService;

    @PostMapping("/add")
    public ResultDto addBack(Integer messageId,
                             Integer toBackId,
                             String content){
        User user = CurrentUserInfo.get();
        if (user==null) {
            ResultDto.buildErrorResultDto(HttpCodeContant.UNAUTHORIZED, "用户未登录");
        }
        return backService.addBack(user,messageId,toBackId,content);
    }
}
