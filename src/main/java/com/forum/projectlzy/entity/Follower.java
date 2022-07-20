package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 关注
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower {

    /**
     * 表中id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 被关注人的用户名
     */
    private Integer followerName;

    /**
     * 创造时间
     */
    private Date createdTime;
}
