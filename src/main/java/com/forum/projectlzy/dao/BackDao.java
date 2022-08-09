package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.Back;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 留言回复的dao层
 */
@Mapper
public interface BackDao extends BaseDao<Back>{

    /**
     *根据id查询留言回复
     * @param id 回复id
     * @return
     */
    Back findBackById(@Param("id") Integer id);

    /**
     * 根据留言id查询下面的回复
     * @param messageId 留言id
     * @param pageNumber 从第pageNumber开始
     * @param limitNumber 每页限制数量
     * @param sortRule 排序规则
     * @param sortPropertyName 根据某个字段进行排序
     * @return
     */
    List<Back> findMessageByPageAndSort(@Param("messageId") Integer messageId, @Param("pageNumber") Integer pageNumber,@Param("limitNumber") Integer limitNumber, @Param("sortRule")String sortRule, @Param("sortPropertyName")String sortPropertyName);
}
