package com.forum.projectlzy.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplePostingDto {
private Integer id;

    private String title;

    private String simpleContent;

    private String fileHtmlString;

    private Integer like;

    private Date lastPublishTime;

    private Integer totalThread;

    private Integer readNumber;
}
