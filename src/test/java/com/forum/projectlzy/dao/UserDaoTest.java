package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;


    private User user;

    @Test
    void testInsertByEntity() {
        user = new User();
        user.setUserName("测试账号");
        user.setPassWord("8080");
        int i = userDao.insertByEntity(user);
        System.out.println(i);
        System.out.println(userDao);
    }
}