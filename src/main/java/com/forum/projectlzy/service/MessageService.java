package com.forum.projectlzy.service;

import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * 留言的业务层
 * @author 扬
 */
public interface MessageService {
    /**
     * 发布留言
     * @param user 用户
     * @param postingId 留言帖子id
     * @param content 留言内容
     * @param photo 图片文件
     * @return
     */
    ResultDto addMessage(User user, Integer postingId, String content, MultipartFile[] photo);

}
