package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户帖子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posting {

    /**
     * 表中帖子id
     */
    private int id;

    /**
     * 发布者账号
     */
    private String publisher;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 帖子状态
     * 0 表示被隐藏, 1表示正常
     */
    private int state;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 正文
     */
    private String context;

    /**
     * 最后回复时间
     */
    private Date lastReplyTime;

    /**
     * 收藏人数
     */
    private int favoriteNumber;

    /**
     * 帖子类型
     * 0 表示文字类型, 1表示视频类型
     */
    private int type;
}
