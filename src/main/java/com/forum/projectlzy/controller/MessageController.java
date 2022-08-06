package com.forum.projectlzy.controller;

import com.forum.projectlzy.constant.HttpCodeContant;
import com.forum.projectlzy.entity.CurrentUserInfo;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *留言控制层
 * @author 扬
 */
@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 发布留言
     * @param postingId 留言帖子id
     * @param content 留言内容
     * @param photo 图片文件
     * @return
     */
    @PostMapping("/add")
    public ResultDto addMessage(Integer postingId,
                                String content,
                                MultipartFile[] photo){
        User user = CurrentUserInfo.get();
        if (user == null) {
            return ResultDto.buildErrorResultDto(HttpCodeContant.UNAUTHORIZED, "用户未登录");
        }
        return messageService.addMessage(user,postingId,content,photo);
    }

}
