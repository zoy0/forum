package com.forum.projectlzy.controller;


import com.forum.projectlzy.constant.HttpCodeContant;
import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.*;
import com.forum.projectlzy.service.PostingService;
import com.forum.projectlzy.utils.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 帖子控制层
 *
 * @author 扬
 */
@RequestMapping("/posting")
@RestController
public class PostingController {

    @Autowired
    private PostingService postingService;

    @Autowired
    private FtpUtils ftpUtils;

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
    @RequestMapping("/list")
    public ResultDto findPosting(String search,
                                 Integer pageNumber,
                                 Integer limitNumber,
                                 Integer type,
                                 String sortRule,
                                 String sortPropertyName) {
        return postingService.findPosting(search, pageNumber, limitNumber, type, sortRule, sortPropertyName);
    }

    /**
     * 发布帖子
     *
     * @param title   标题
     * @param content 正文
     * @param photo   照片
     * @return
     */
    @PostMapping("/add")
    public ResultDto addPosting(String title,
                                String content,
                                MultipartFile[] photo) {
        User user = CurrentUserInfo.get();
        if (user == null) {
            return ResultDto.buildErrorResultDto(HttpCodeContant.UNAUTHORIZED, "用户未登录");
        }
        return postingService.addPosting(user, title, content, photo);
    }


    /**
     * 获取照片文件的流
     *
     * @param response response
     * @param fileName 文件名
     */
    @RequestMapping("/photo/{fileName}")
    public void getStreamData(HttpServletResponse response,
                              @PathVariable("fileName") String fileName) {

        InputStream in = null;
        OutputStream os = null;

        try {
            in = ftpUtils.download(fileName, FtpUtils.IMG_PATH);
            BufferedImage bufImg = null;
            bufImg = ImageIO.read(in);
            os = response.getOutputStream();
            ImageIO.write(bufImg, "png", os);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

    }

    /**
     * 由帖子id获取帖子详情
     *
     * @param postingId 帖子id
     * @return
     */
    @GetMapping("/detail/{postingId}")
    public ResultDto getDetailedPosting(@PathVariable("postingId") Integer postingId) {
        return postingService.getPostingById(postingId);
    }


}
