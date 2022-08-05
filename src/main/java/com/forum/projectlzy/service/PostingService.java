package com.forum.projectlzy.service;

import com.forum.projectlzy.entity.ResultDto;
import com.forum.projectlzy.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * 帖子业务层
 *
 * @author 扬
 */
public interface PostingService {
    /**
     * 根据条件搜索帖子
     *
     * @param search           模糊搜索内容
     * @param pageNumber       页数
     * @param limitNumber      每页条数
     * @param type             帖子类型
     * @param sortRule         升/降序
     * @param sortPropertyName 由哪个字段决定升降序
     * @return list结果
     */
    ResultDto findPosting(String search, Integer pageNumber, Integer limitNumber, Integer type, String sortRule, String sortPropertyName);

    /**
     * 添加帖子
     *
     * @param user    添加者
     * @param title   标题
     * @param content 内容
     * @param photo   照片, 没有则为空
     * @return
     */
    ResultDto addPosting(User user, String title, String content, MultipartFile[] photo);

    /**
     * 由帖子id获取帖子详情信息
     *
     * @param postingId 帖子id
     * @return
     */
    ResultDto getPostingById(Integer postingId);
}
