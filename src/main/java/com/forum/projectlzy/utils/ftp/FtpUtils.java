package com.forum.projectlzy.utils.ftp;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * ftp工具类
 */
@Component
public class FtpUtils {

    /**
     * 基础路径
     */
    @Value("${ftp.basePath}")
    private String ftpBasePath;

    @Autowired
    private FtpPool ftpPool;

    /**
     * 文件类型追加路径
     */
    public static String IMG_PATH = "/img";

    /**
     * 视频类型追加路径
     */
    public static String VIDEO_PATH = "/video";


    /**
     * 上传
     * @param map  String 为文件名 , InputStream 为文件输入流
     * @param path 追加路径
     */
    public void upload(Map<String, InputStream> map, String path) {

        try {

            FTPClient ftp = ftpPool.getFtpClient();
            //创建文件夹
            ftp.makeDirectory(ftpBasePath + path);
            //存储文件
            ftp.changeWorkingDirectory(ftpBasePath + path);
            ftp.enterLocalPassiveMode();
            map.forEach((fileName, inputStream) -> {
                try {
                    ftp.storeFile(fileName, inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ftp.enterLocalActiveMode();
            ftpPool.returnFtpClient(ftp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取/下载
     * @param fileName 文件名
     * @param path 追加路径
     * @return
     */
    public InputStream download(String fileName, String path) {
        InputStream inputStream = null;
        try {
            FTPClient ftpClient = ftpPool.getFtpClient();
            ftpClient.configure(new FTPClientConfig(FTPClientConfig.SYST_UNIX));
            ftpClient.changeWorkingDirectory(ftpBasePath + path);
            ftpClient.enterLocalPassiveMode();
            inputStream = ftpClient.retrieveFileStream(fileName);
            ftpPool.returnFtpClient(ftpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

}
