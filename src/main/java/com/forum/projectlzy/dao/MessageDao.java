package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao extends BaseDao<Message> {

    /**
     *
     * @param postingId
     * @param pageNumber
     * @param limitNumber
     * @param sortRule
     * @param sortPropertyName
     * @return
     */
    List<Message> findMessageByPageAndSort(@Param("postingId") Integer postingId,@Param("pageNumber") Integer pageNumber,@Param("limitNumber") Integer limitNumber, @Param("sortRule")String sortRule, @Param("sortPropertyName")String sortPropertyName);
}
