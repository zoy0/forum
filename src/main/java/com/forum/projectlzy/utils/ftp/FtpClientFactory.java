package com.forum.projectlzy.utils.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * FtpClient的工厂, 用于创建ftp连接池
 */
@Component
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {


    /**
     * 地址
     */
    @Value("${ftp.address}")
    private  String FTP_ADDRESS;

    /**
     * 端口号
     */
    @Value("${ftp.port}")
    private  Integer FTP_PORT;

    /**
     * 用户名
     */
    @Value("${ftp.username}")
    private  String FTP_USERNAME;

    /**
     * 密码
     */
    @Value("${ftp.password}")
    private  String FTP_PASSWORD;

    /**
     * 编码格式
     */
    @Value("${ftp.encoding}")
    private  String FTP_ENCODING;

    /**
     * 基础路径
     */
    @Value("${ftp.basePath}")
    private  String FTP_BASE_PATH;


    /**
     * 用于对象的新建
     */
    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(FTP_ADDRESS, FTP_PORT);
        ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
        ftpClient.setControlEncoding(FTP_ENCODING);
        ftpClient.makeDirectory(FTP_BASE_PATH);
        ftpClient.changeWorkingDirectory(FTP_BASE_PATH);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * 销毁一个对象
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检验这个对象是否还有有效
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            return ftpClient.sendNoOp();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 在借用一个对象的时候调用
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {

    }

    /**
     * 在归还一个对象的时候调用
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {

    }
}
