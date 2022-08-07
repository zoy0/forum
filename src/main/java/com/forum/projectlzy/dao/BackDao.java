package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.Back;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
