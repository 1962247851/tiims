package com.example.tiims.common;


import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 通用增删改查Service
 *
 * @author qq1962247851
 * @date 2020/6/16 22:51
 */
public interface CrudService<E, K> {
    /**
     * 插入
     *
     * @param e 实体
     * @return 插入到数据库后的实体
     */
    @NotNull
    E insert(E e);

    /**
     * 删除
     *
     * @param id 主键
     * @return 是否成功
     */
    @NotNull
    Boolean delete(K id);

    /**
     * 更新
     *
     * @param e 实体
     * @return 更新后的实体
     */
    @NotNull
    E update(E e);

    /**
     * 根据主键查询数据
     *
     * @param id 主键
     * @return 实体
     */
    @Nullable
    E query(K id);

    /**
     * 分页查询所有数据
     *
     * @param page 页数，从0开始
     * @param size 每一页的大小
     * @return 所有数据
     */
    Map<String, Object> queryAll(Integer page, Integer size);

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    List<E> queryAll();

}
