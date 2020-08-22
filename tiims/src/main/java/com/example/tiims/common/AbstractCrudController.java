package com.example.tiims.common;

import com.example.tiims.common.entity.JsonResult;
import org.springframework.web.bind.annotation.*;

/**
 * 增删改查控制器
 *
 * @author qq1962247851
 * @date 2020/6/16 23:05
 */
public abstract class AbstractCrudController<E, K> {

    /**
     * 新增
     *
     * @param e 实体
     * @return JsonString
     */
    @PutMapping("insert")
    public abstract JsonResult<?> insert(E e);

    /**
     * 删除
     *
     * @param k 主键
     * @return JsonString
     */
    @DeleteMapping("delete")
    public abstract JsonResult<?> delete(K k);

    /**
     * 更新
     *
     * @param e 实体
     * @return JsonString
     */
    @PostMapping("update")
    public abstract JsonResult<?> update(E e);

    /**
     * 查询
     *
     * @param k    主键
     * @param page 第几页，从0开始
     * @param size 每页的大小
     * @return JsonString
     */
    @GetMapping("query")
    public abstract JsonResult<?> query(K k, Integer page, Integer size);
}
