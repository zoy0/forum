package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 帖子的留言
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    /**
     * 表中留言id
     */
    private Integer id;

    /**
     * 留言帖子的id
     */
    private Integer postingId;

    /**
     * 留言的发布者
     */
    private String publisher;

    /**
     * 所在楼数
     */
    private Integer threadNumber;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 正文
     */
    private String content;

    /**
     * 回复数量
     */
    private Integer backNumber;

}
