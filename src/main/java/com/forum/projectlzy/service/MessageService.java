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

    /**
     * 搜索某个帖子下的留言
     * @param postingId 帖子id
     * @param pageNumber       页数
     * @param limitNumber      每页条数
     * @param sortRule         升/降序
     * @param sortPropertyName 由哪个字段决定升降序
     * @return
     */
    ResultDto findMessage(Integer postingId, Integer pageNumber, Integer limitNumber, String sortRule, String sortPropertyName);
}
