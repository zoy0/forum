package com.forum.projectlzy.controller;

import com.forum.projectlzy.constant.HttpCodeContant;
import com.forum.projectlzy.entity.CurrentUserInfo;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.BackService;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 搜索某个帖子下的留言
     * @param messageId 留言id
     * @param pageNumber       页数
     * @param limitNumber      每页条数
     * @param sortRule         升/降序
     * @param sortPropertyName 由哪个字段决定升降序
     * @return
     */
    @GetMapping("/message/{messageId}")
    public ResultDto findMessage(@PathVariable("messageId") Integer messageId,
                                 Integer pageNumber,
                                 Integer limitNumber,
                                 String sortRule,
                                 String sortPropertyName){
        if (pageNumber==null||limitNumber==null){
            pageNumber=1;
            limitNumber=5;
        }
        return backService.findBackByMessageId(messageId,pageNumber,limitNumber,sortRule,sortPropertyName);
    }
}
