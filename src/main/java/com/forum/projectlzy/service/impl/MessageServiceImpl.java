package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.MessageDao;
import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.*;
import com.forum.projectlzy.service.MessageService;
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
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private PostingDao postingDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Override
    public ResultDto addMessage(User user, Integer postingId, String content, MultipartFile[] photo) {
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
        Posting posting = postingDao.findById(postingId);
        Message message=new Message();
        message.setPostingId(postingId);
        message.setPublisher(user.getUserName());
        message.setContent(content);
        message.setPublishTime(new Date());
        message.setThreadNumber(posting.getTotalThread()+1);
        messageDao.insertByEntity(message);
        Map<String,Object> postingMap=new HashMap<>();
        postingMap.put("totalThread",posting.getTotalThread()+1);
        postingDao.updatePostingById(postingId,postingMap);
        if (!map.isEmpty()) {
            ftpUtils.upload(map, FtpUtils.IMG_PATH);
            log.info("用户 {} 日期 {} 帖子id {} 上传文件 :{}", user.getUserName(),message.getPublishTime(),postingId , map.keySet());
        }
        return ResultDto.buildSuccessResultDto("留言成功",null);
    }

    @Override
    public ResultDto findMessage(Integer postingId, Integer pageNumber, Integer limitNumber, String sortRule, String sortPropertyName) {
        List<Message> list =messageDao.findMessageByPageAndSort(postingId,(pageNumber - 1) * limitNumber, limitNumber, sortRule, sortPropertyName);
        return ResultDto.buildSuccessResultDto(null, list);
    }
}
