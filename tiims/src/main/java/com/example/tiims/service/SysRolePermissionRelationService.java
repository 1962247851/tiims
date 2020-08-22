package com.example.tiims.service;

import com.sun.istack.Nullable;
import com.example.tiims.entity.SysRolePermissionRelation;

import java.util.List;

/**
 * 路径权限关联表(SysRolePermissionRelationService)表服务接口
 */
public interface SysRolePermissionRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Nullable
    SysRolePermissionRelation queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRolePermissionRelation> queryAllByLimit(Long offset, Long limit);

    /**
     * 新增数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 实例对象
     */
    SysRolePermissionRelation insert(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 修改数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 实例对象
     */
    SysRolePermissionRelation update(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过roleId查询多条数据
     *
     * @param roleId roleId
     * @return 实例对象
     */
    List<SysRolePermissionRelation> queryAllByRoleId(Long roleId);

    /**
     * 通过permissionId查询单条数据
     *
     * @param permissionId permissionId
     * @return 实例对象
     */
    List<SysRolePermissionRelation> queryAllByPermissionId(Long permissionId);

    /**
     * 通过roleId和permissionId查询单条数据
     *
     * @param roleId       roleId
     * @param permissionId permissionId
     * @return 实例对象
     */
    @Nullable
    SysRolePermissionRelation queryByRoleIdAndPermissionId(Long roleId, Long permissionId);

}