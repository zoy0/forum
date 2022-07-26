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
    private Integer id;

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
    private Integer postingState;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 正文
     */
    private String content;

    /**
     * 最后回复时间
     */
    private Date lastReplyTime;

    /**
     * 收藏人数
     */
    private Integer favoriteNumber;

    /**
     * 帖子类型
     * 0 表示文字类型, 1表示视频类型
     */
    private Integer postingType;

    /**
     * 留言层数
     */
    private Integer totalThread;

    /**
     * 游览次数
     */
    private Integer readNumber;
}
