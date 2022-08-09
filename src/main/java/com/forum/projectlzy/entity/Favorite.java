package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 用户收藏
 */
public class Favorite {

    /**
     * 表中id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 收藏帖子id
     */
    private Integer postingId;

    /**
     * 创造时间
     */
    private Date createdTime;

}
