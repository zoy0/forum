package com.forum.projectlzy;

import com.forum.projectlzy.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
public class ProjectLzyApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProjectLzyApplication.class, args);
    }





}
