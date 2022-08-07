package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 帖子下留言的回复
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Back {

    /**
     * 表中回复id
     */
    private Integer id;

    /**
     * 所回复的留言id
     */
    private Integer messageId;

    /**
     * 发布者
     */
    private String publisher;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 正文
     */
    private String content;

    /**
     * 该回复所回复的id
     */
    private Integer toBackId;
}
