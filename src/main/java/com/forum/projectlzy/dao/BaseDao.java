package com.forum.projectlzy.dao;

import java.util.List;
import java.util.Map;

/**
 * Dao基类
 * @param <T>
 */
public interface BaseDao<T> {

    /**
     * 升序关键字
     */
    public static final String ASC_SORT="ASC";

    /**
     * 降序关键字
     */
    public static final String DESC_SORT="DESC";


    /**
     * 根据实体类进行插入
     * @param t 带有即将插入的参数的实体类
     * @return 影响行数
     */
    int insertByEntity(T t);

    /**
     * 根据 field - value 的map集合进行插入
     * @param map 带有即将插入的属性名 - 参数的map集合
     * @return 影响行数
     */
    int insertByMap(Map<String, Object> map);

    /**
     * 根据实体类进行删除
     * @param t 带有即将插入的参数的实体类
     * @return 影响行数
     */
    int deleteByEntity(T t);

    /**
     * 根据 field - value 的map集合进行删除
     * @param map 带有即将删除的属性名 - 参数的map集合
     * @return 影响行数
     */
    int deleteByMap(Map<String, Object> map);

    /**
     * 根据 field - value 的map集合进行查询并修改
     * @param conditionMap 带有即将条件的属性名 - 参数的map集合
     * @param modifiedMap 带有修改后的属性名 - 参数的map集合
     * @return
     */
    int updateByMap(Map<String, Object> conditionMap , Map<String, Object> modifiedMap);

    /**
     * 根据实体类进行查找
     * @param t 带有即将插入的参数的实体类
     * @return 查询结果
     */
    List<T> findByEntity(T t);

    /**
     * 根据 field - value 的map集合进行查询
     * @param map 带有即将查询的属性名 - 参数的map集合
     * @return 查询结果
     */
    List<T> findByMap(Map<String, Object> map);


}
