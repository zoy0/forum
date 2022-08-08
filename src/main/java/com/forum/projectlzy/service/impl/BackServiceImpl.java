package com.forum.projectlzy.service.impl;

import com.forum.projectlzy.dao.BackDao;
import com.forum.projectlzy.dao.MessageDao;
import com.forum.projectlzy.entity.Back;
import com.forum.projectlzy.entity.Message;
import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.service.BackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BackServiceImpl implements BackService {

    @Resource
    private BackDao backDao;

    @Resource
    private MessageDao messageDao;

    Pattern pattern = Pattern.compile("回复 (\\S*) :");

    @Override
    public ResultDto addBack(User user, Integer messageId, Integer toBackId, String content) {
        if (toBackId != null) {
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                String username = matcher.group(1);
                Back toBack = backDao.findBackById(toBackId);
                if (!toBack.getPublisher().equals(username)) {
                    toBackId=null;
                }
            }else {
                toBackId=null;
            }
        }
        Back back = new Back();
        back.setMessageId(messageId);
        back.setToBackId(toBackId);
        back.setContent(content);
        back.setPublisher(user.getUserName());
        back.setPublishTime(new Date());
        backDao.insertByEntity(back);
        Message message = messageDao.findById(messageId);
        Map<String , Object> map = new HashMap<>();
        map.put("backNumber",message.getBackNumber()+1);
        messageDao.updateMessageById(messageId,map);
        return ResultDto.buildSuccessResultDto("回复成功",null);
    }

    @Override
    public ResultDto findBackByMessageId(Integer messageId, Integer pageNumber, Integer limitNumber, String sortRule, String sortPropertyName) {
        List<Back> list = backDao.findMessageByPageAndSort(messageId,(pageNumber - 1) * limitNumber, limitNumber, sortRule, sortPropertyName);
        return ResultDto.buildSuccessResultDto(null,list);
    }
}
