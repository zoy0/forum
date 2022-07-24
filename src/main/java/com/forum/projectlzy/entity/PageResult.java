package com.forum.projectlzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Integer pageNumber;

    private Integer totalNumber;

    private List<T> list;
}
