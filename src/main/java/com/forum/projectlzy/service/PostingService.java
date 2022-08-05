package com.forum.projectlzy.service;

import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface PostingService {
    ResultDto findPosting(String search, Integer pageNumber, Integer limitNumber, Integer type, String sortRule, String sortPropertyName);

    ResultDto addPosting(User user , String title, String content, MultipartFile[] photo);

    ResultDto getPostingById(Integer postingId);
}
