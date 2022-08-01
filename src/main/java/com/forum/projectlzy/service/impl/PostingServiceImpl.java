package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.PageResult;
import com.forum.projectlzy.entity.Posting;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.PostingService;
import com.forum.projectlzy.utils.ftp.FtpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Slf4j
@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    private PostingDao postingDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Override
    public ResultDto findPosting(String search, Integer pageNumber, Integer limitNumber, Integer type, String sortRule, String sortPropertyName) {
        Posting posting = new Posting();
        posting.setPostingType(type);
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

    @Override
    public ResultDto addPosting(User user, String title, String content, MultipartFile[] photo) {
        Map<String, InputStream> map = new HashMap<>();
        for (MultipartFile f :
                photo) {
            String originalFilename = f.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            content =content.replace("<img  src='" + originalFilename + "' style=\"width: 500px \"></img>",
                    "<img  src='/posting/photo/" + fileName + "' style=\"width: 500px \"></img>");
            try {
                map.put(fileName,f.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Posting posting=new Posting();
        posting.setTitle(title);
        posting.setContent(content);
        posting.setPublisher(user.getUserName());
        posting.setPublishTime(new Date());
        posting.setPostingType(0);
        posting.setLastReplyTime(new Date());
        postingDao.insertByEntity(posting);
        ftpUtils.upload(map, FtpUtils.IMG_PATH);
        log.info("用户 {} 日期 {} 发布帖子 {} 上传文件 :{}",user.getUserName(),posting.getPublishTime(),posting.getTitle(),map.keySet());
        return ResultDto.buildSuccessResultDto("发布成功", null);
    }


}
