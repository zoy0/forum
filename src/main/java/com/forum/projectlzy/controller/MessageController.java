package com.forum.projectlzy.controller;

import com.forum.projectlzy.constant.HttpCodeContant;
import com.forum.projectlzy.entity.CurrentUserInfo;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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


    /**
     * 搜索某个帖子下的留言
     * @param postingId 帖子id
     * @param pageNumber       页数
     * @param limitNumber      每页条数
     * @param sortRule         升/降序
     * @param sortPropertyName 由哪个字段决定升降序
     * @return
     */
    @GetMapping("/{postingId}")
    public ResultDto findMessage(@PathVariable("postingId") Integer postingId,
                                 Integer pageNumber,
                                 Integer limitNumber,
                                 String sortRule,
                                 String sortPropertyName){
        if (pageNumber==null||limitNumber==null){
            pageNumber=1;
            limitNumber=10;
        }
        return messageService.findMessage(postingId,pageNumber,limitNumber,sortRule,sortPropertyName);
    }
}
