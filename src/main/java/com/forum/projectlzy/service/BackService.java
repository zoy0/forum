package com.forum.projectlzy.service;


import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;

/**
 * 回复留言的业务层
 */
public interface BackService {

    ResultDto addBack(User user, Integer messageId, Integer toBackId, String content);
}
