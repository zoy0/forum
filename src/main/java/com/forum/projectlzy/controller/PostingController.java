package com.forum.projectlzy.controller;


import com.forum.projectlzy.constant.HttpCodeContant;
import com.forum.projectlzy.dao.PostingDao;
import com.forum.projectlzy.entity.*;
import com.forum.projectlzy.service.PostingService;
import com.forum.projectlzy.utils.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RequestMapping("/posting")
@RestController
public class PostingController {

    @Autowired
    private PostingService postingService;

    @Autowired
    private FtpUtils ftpUtils;

    @RequestMapping("/list")
    public ResultDto findPosting(String search,
                                 Integer pageNumber,
                                 Integer limitNumber,
                                 Integer type,
                                 String sortRule,
                                 String sortPropertyName) {
        return postingService.findPosting(search, pageNumber, limitNumber, type, sortRule, sortPropertyName);
    }

    @PostMapping("/add")
    public ResultDto addPosting(String title,
                                String content,
                                MultipartFile[] photo)  {
        User user = CurrentUserInfo.get();
        if (user==null){
            return ResultDto.buildErrorResultDto(HttpCodeContant.UNAUTHORIZED,"用户未登录");
        }
        return postingService.addPosting(user,title,content,photo);
    }


    @RequestMapping("/photo/{fileName}")
    public void getStreamData(HttpServletResponse response,
                              @PathVariable("fileName")String fileName) {

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
        }finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

    }

}
