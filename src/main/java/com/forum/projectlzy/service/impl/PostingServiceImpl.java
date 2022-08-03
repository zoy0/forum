package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.PageResult;
import com.forum.projectlzy.entity.Posting;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.entity.dto.SimplePostingDto;
import com.forum.projectlzy.service.PostingService;
import com.forum.projectlzy.utils.ftp.FtpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Slf4j
@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    private PostingDao postingDao;

    @Autowired
    private FtpUtils ftpUtils;

    Pattern pattern = Pattern.compile("<img  src='/posting/photo/(\\S*)' style=\"width: 500px \"></img>");

    @Override
    public ResultDto findPosting(String search, Integer pageNumber, Integer limitNumber, Integer type, String sortRule, String sortPropertyName) {
        Posting searchedPosting = new Posting();
        searchedPosting.setPostingType(type);
        pageNumber = 1;
        limitNumber = 10;

        List<Posting> list = postingDao.findByPageAndSort(search, searchedPosting, (pageNumber - 1) * limitNumber, limitNumber, sortRule, sortPropertyName);

        List<SimplePostingDto> simplePostingList = list.stream().map(posting -> {
            String content = posting.getContent();
            Matcher matcher = pattern.matcher(content);
            String imgString = null;
            if (matcher.find()) {
                imgString = matcher.group();
                content = content.replace(matcher.group(), " ");
            }
            while (matcher.find()) {
                content = content.replace(matcher.group(), " ");
            }
            if (content.length() > 40) {
                content = content.substring(0, 40) + "...";
            }
            return new SimplePostingDto(posting.getId(),posting.getTitle(), content, imgString, posting.getFavoriteNumber(), posting.getPublishTime());
        }).collect(Collectors.toList());
//        TODO
//        video兼容
        Integer count = postingDao.countBySearch(search, type);

        PageResult<SimplePostingDto> result = new PageResult<>(pageNumber, count, simplePostingList);

        return ResultDto.buildSuccessResultDto("", result);


    }

    @Override
    public ResultDto addPosting(User user, String title, String content, MultipartFile[] photo) {
        Map<String, InputStream> map = new HashMap<>();
        for (MultipartFile f :
                photo) {
            String originalFilename = f.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            content = content.replace("<img  src='" + originalFilename + "' style=\"width: 500px \"></img>",
                    "<img  src='/posting/photo/" + fileName + "' style=\"width: 500px \"></img>");
            try {
                map.put(fileName, f.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Posting posting = new Posting();
        posting.setTitle(title);
        posting.setContent(content);
        posting.setPublisher(user.getUserName());
        posting.setPublishTime(new Date());
        posting.setPostingType(0);
        posting.setLastReplyTime(new Date());
        postingDao.insertByEntity(posting);
        ftpUtils.upload(map, FtpUtils.IMG_PATH);
        log.info("用户 {} 日期 {} 发布帖子 {} 上传文件 :{}", user.getUserName(), posting.getPublishTime(), posting.getTitle(), map.keySet());
        return ResultDto.buildSuccessResultDto("发布成功", null);
    }


}
