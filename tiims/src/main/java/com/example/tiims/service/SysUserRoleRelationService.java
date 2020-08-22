package com.example.tiims.service;

import com.sun.istack.Nullable;
import com.example.tiims.entity.SysUserRoleRelation;

import java.util.List;

/**
 * 路径权限关联表(SysUserRoleRelationService)表服务接口
 */
public interface SysUserRoleRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Nullable
    SysUserRoleRelation queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUserRoleRelation> queryAllByLimit(Long offset, Long limit);

    /**
     * 新增数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 实例对象
     */
    SysUserRoleRelation insert(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 修改数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 实例对象
     */
    SysUserRoleRelation update(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过userId查询多条数据
     *
     * @param userId userId
     * @return 实例对象
     */
    List<SysUserRoleRelation> queryAllByUserId(Long userId);

    /**
     * 通过roleId查询多条数据
     *
     * @param roleId roleId
     * @return 实例对象
     */
    List<SysUserRoleRelation> queryAllByRoleId(Long roleId);

    /**
     * 通过userId和roleId查询单条数据
     *
     * @param userId userId
     * @param roleId roleId
     * @return 实例对象
     */
    SysUserRoleRelation queryByUserIdAndRoleId(Long userId, Long roleId);

    List<SysUserRoleRelation> queryAll();
}