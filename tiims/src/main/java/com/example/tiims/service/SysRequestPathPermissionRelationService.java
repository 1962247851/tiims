package com.example.tiims.service;

import com.sun.istack.Nullable;
import com.example.tiims.entity.SysRequestPathPermissionRelation;

import java.util.List;

/**
 * 路径权限关联表(SysRequestPathPermissionRelation)表服务接口
 */
public interface SysRequestPathPermissionRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Nullable
    SysRequestPathPermissionRelation queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRequestPathPermissionRelation> queryAllByLimit(Long offset, Long limit);

    /**
     * 新增数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    SysRequestPathPermissionRelation insert(SysRequestPathPermissionRelation sysRequestPathPermissionRelation);

    /**
     * 修改数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    SysRequestPathPermissionRelation update(SysRequestPathPermissionRelation sysRequestPathPermissionRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过URL_ID查询多条数据
     *
     * @param urlId URL_ID
     * @return 实例对象
     */
    List<SysRequestPathPermissionRelation> queryListByUrlId(Long urlId);

    /**
     * 通过permissionId查询多条数据
     *
     * @param permissionId permissionId
     * @return 实例对象
     */
    List<SysRequestPathPermissionRelation> queryListByPermissionId(Long permissionId);

    /**
     * 通过requestPathId和permissionId查询单条数据
     *
     * @param requestPathId requestPathId
     * @param permissionId  permissionId
     * @return 实例对象
     */
    SysRequestPathPermissionRelation queryByRequestPathIdAndPermissionId(Long requestPathId, Long permissionId);

}