package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.PageResult;
import com.forum.projectlzy.entity.Posting;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    private PostingDao postingDao;

    @Override
    public ResultDto findPosting(String search, Integer pageNumber, Integer limitNumber, Integer type, String sortRule, String sortPropertyName) {
        Posting posting = new Posting();
        posting.setType(type);
        pageNumber= 1;
        limitNumber=10;
        List<Posting> list = postingDao.findByPageAndSort(search, posting, (pageNumber - 1) * limitNumber, limitNumber, sortRule, sortPropertyName);
//        TODO
//        对list集合进行裁剪, 使得代码框的code 可以在首页完全或不完全的展示出来, 而 video 和 img 则选择性的 放一个 / 一张出来
//        文字+代码限制在20字内      图片和视频限制一张 / 一个 (如果type是1, 即为视频, 可以忽略, 因为视频类型的帖子正文只有视频链接)
        Integer count = postingDao.countBySearch(search, type);

        PageResult<Posting> result = new PageResult<>(pageNumber, count, list);

        return ResultDto.buildSuccessResultDto("",result);



    }
}
