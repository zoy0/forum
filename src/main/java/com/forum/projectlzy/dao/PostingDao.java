package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.PageResult;
import com.forum.projectlzy.entity.Posting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostingDao extends BaseDao<Posting> {

    /**
     * 通过页数和排序方法进行查询
     *
     * @param search           模糊查询的关键字
     * @param pageNumber       第 pageNumber 页
     * @param limitNumber      每页限制数量
     * @param sortRule         排序规则
     * @param sortPropertyName 按属性名排序
     * @return
     */
    List<Posting> findByPageAndSort(@Param("search") String search,
                                    @Param("pageNumber") Integer pageNumber,
                                    @Param("limitNumber") Integer limitNumber,
                                    @Param("sortRule") String sortRule,
                                    @Param("sortPropertyName") String sortPropertyName);

    Integer countBySearch(@Param("search") String search);

}
