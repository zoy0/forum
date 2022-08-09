package com.forum.projectlzy.service;


import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;

/**
 * 回复留言的业务层
 */
public interface BackService {

    /**
     * 添加留言回复
     * @param user 用户
     * @param messageId 留言id
     * @param toBackId 回复id
     * @param content 内容
     * @return
     */
    ResultDto addBack(User user, Integer messageId, Integer toBackId, String content);

    /**
     * 根据留言id查询下面的回复
     * @param messageId 留言id
     * @param pageNumber 页数
     * @param limitNumber 每页限制数量
     * @param sortRule 升/降序
     * @param sortPropertyName 根据xx进行排序
     * @return
     */
    ResultDto findBackByMessageId(Integer messageId, Integer pageNumber, Integer limitNumber, String sortRule, String sortPropertyName);
}
