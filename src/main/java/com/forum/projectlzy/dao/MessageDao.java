package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageDao extends BaseDao<Message> {

    /**
     * 根据帖子id搜索下面的留言
     * @param postingId 帖子id
     * @param pageNumber 从第pageNumber开始
     * @param limitNumber 每页限制数量
     * @param sortRule 排序规则
     * @param sortPropertyName 根据某个字段进行排序
     * @return
     */
    List<Message> findMessageByPageAndSort(@Param("postingId") Integer postingId,@Param("pageNumber") Integer pageNumber,@Param("limitNumber") Integer limitNumber, @Param("sortRule")String sortRule, @Param("sortPropertyName")String sortPropertyName);

    /**
     *根据id查询留言
     * @param id 留言id
     * @return
     */
    Message findById(@Param("id") Integer id);

    /**
     *根据id更新留言
     * @param id 留言id
     * @param map map
     * @return
     */
    Integer updateMessageById(@Param("id") Integer id,@Param("message") Map<String, Object> map);
}
