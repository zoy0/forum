package com.forum.projectlzy.controller;


import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.PageResult;
import com.forum.projectlzy.entity.Posting;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/posting")
@RestController
public class PostingController {

    @Autowired
    private PostingService postingService;

    @RequestMapping("/list")
    public ResultDto findPosting(String search,
                                 Integer pageNumber,
                                 Integer limitNumber,
                                 Integer type,
                                 String sortRule,
                                 String sortPropertyName) {
            return postingService.findPosting(search,pageNumber,limitNumber,type,sortRule,sortPropertyName);
    }

}
