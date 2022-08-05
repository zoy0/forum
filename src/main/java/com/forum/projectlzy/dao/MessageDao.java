package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao extends BaseDao<Message> {

}
