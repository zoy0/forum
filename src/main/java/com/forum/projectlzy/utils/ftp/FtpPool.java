package com.forum.projectlzy.utils.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * ftp连接池
 */
@Component
public class FtpPool {


    private final GenericObjectPool<FTPClient> internalPool;

    public FtpPool(@Autowired FtpClientFactory ftpClientFactory ,@Autowired Environment environment) {

        GenericObjectPoolConfig<FTPClient> config=new GenericObjectPoolConfig<>();
        config.setMaxTotal(Integer.parseInt(Objects.requireNonNull(environment.getProperty("ftpPool.maxTotal"))));
        config.setMinIdle(Integer.parseInt(Objects.requireNonNull(environment.getProperty("ftpPool.minIdle"))));
        config.setMaxIdle(Integer.parseInt(Objects.requireNonNull(environment.getProperty("ftpPool.maxIdle"))));
        config.setMaxWaitMillis(Integer.parseInt(Objects.requireNonNull(environment.getProperty("ftpPool.maxWaitMillis"))));
        this.internalPool = new GenericObjectPool<FTPClient>(ftpClientFactory,config);
    }

    /**
     * 从连接池中取连接
     */
    FTPClient getFtpClient() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将链接归还到连接池
     */
    void returnFtpClient(FTPClient ftpClient) {
        try {
            internalPool.returnObject(ftpClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
