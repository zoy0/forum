package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户Dao类
 */
@Mapper
public interface UserDao extends BaseDao<User> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return
     */
    List<User> findByUserName(@Param("userName")String username);

}
