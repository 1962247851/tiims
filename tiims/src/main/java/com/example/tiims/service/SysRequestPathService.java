package com.example.tiims.service;

import com.sun.istack.Nullable;
import com.example.tiims.entity.SysRequestPath;

import java.util.List;

/**
 * 请求路径(SysRequestPath)表服务接口
 */
public interface SysRequestPathService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Nullable
    SysRequestPath queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRequestPath> queryAllByLimit(Long offset, Long limit);

    /**
     * 新增数据
     *
     * @param sysRequestPath 实例对象
     * @return 实例对象
     */
    SysRequestPath insert(SysRequestPath sysRequestPath);

    /**
     * 修改数据
     *
     * @param sysRequestPath 实例对象
     * @return 实例对象
     */
    SysRequestPath update(SysRequestPath sysRequestPath);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过URL查询单条数据
     *
     * @param url 主键
     * @return 实例对象
     */
    @Nullable
    SysRequestPath queryByUrl(String url);

    /**
     * 查询多条数据
     *
     * @return 多条数据
     */
    List<SysRequestPath> queryAll();
}