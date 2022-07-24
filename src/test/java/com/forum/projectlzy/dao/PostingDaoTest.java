package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.Posting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostingDaoTest {

    private String search = null;

    @Autowired
    private PostingDao dao;

    @Test
    void findByPageAndSort() {
        List<Posting> list1 = dao.findByPageAndSort(search,0,10,dao.ASC_SORT,"publish_time");
        List<Posting> list2 = dao.findByPageAndSort(search,0,10,dao.DESC_SORT,"publish_time");
        System.out.println(list1);
        System.out.println(list2);
    }

    @Test
    void countBySearch() {
        Integer count = dao.countBySearch(search);
        System.out.println(count);
    }
}