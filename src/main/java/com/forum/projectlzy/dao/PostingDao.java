package com.forum.projectlzy.dao;

import com.forum.projectlzy.entity.PageResult;
import com.forum.projectlzy.entity.Posting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 帖子dao层
 */
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
                                    @Param("posting") Posting posting,
                                    @Param("pageNumber") Integer pageNumber,
                                    @Param("limitNumber") Integer limitNumber,
                                    @Param("sortRule") String sortRule,
                                    @Param("sortPropertyName") String sortPropertyName);

    /**
     * 由模糊搜索的内容获取结果数
     *
     * @param search 模糊搜索
     * @param type   类型
     * @return
     */
    Integer countBySearch(@Param("search") String search, @Param("type") Integer type);

    /**
     * 通过帖子id搜索
     *
     * @param id 帖子id
     * @return
     */
    Posting findById(@Param("id") Integer id);
}
