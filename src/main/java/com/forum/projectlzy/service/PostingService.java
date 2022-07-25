package com.forum.projectlzy.service;

import com.forum.projectlzy.entity.ResultDto;

public interface PostingService {
    ResultDto findPosting(String search, Integer pageNumber, Integer limitNumber, Integer type, String sortRule, String sortPropertyName);
}
