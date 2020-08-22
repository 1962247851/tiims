package com.example.tiims.service.impl;

import org.springframework.stereotype.Service;
import com.example.tiims.dao.SysRolePermissionRelationDao;
import com.example.tiims.entity.SysRolePermissionRelation;
import com.example.tiims.service.SysRolePermissionRelationService;

import java.util.List;

/**
 * 角色权限关联表(SysRolePermissionRelation)表服务实现类
 */
@Service
public class SysRolePermissionRelationServiceImpl implements SysRolePermissionRelationService {

    private final SysRolePermissionRelationDao sysRolePermissionRelationDao;

    public SysRolePermissionRelationServiceImpl(SysRolePermissionRelationDao sysRolePermissionRelationDao) {
        this.sysRolePermissionRelationDao = sysRolePermissionRelationDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRolePermissionRelation queryById(Long id) {
        return sysRolePermissionRelationDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRolePermissionRelation> queryAllByLimit(Long offset, Long limit) {
        return sysRolePermissionRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolePermissionRelation insert(SysRolePermissionRelation sysRequestPathPermissionRelation) {
        return sysRolePermissionRelationDao.save(sysRequestPathPermissionRelation);
    }

    /**
     * 修改数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolePermissionRelation update(SysRolePermissionRelation sysRequestPathPermissionRelation) {
        return sysRolePermissionRelationDao.saveAndFlush(sysRequestPathPermissionRelation);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        try {
            sysRolePermissionRelationDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysRolePermissionRelationDao.findById(id).isEmpty();
    }

    @Override
    public List<SysRolePermissionRelation> queryAllByRoleId(Long roleId) {
        return sysRolePermissionRelationDao.findAllByRoleId(roleId);
    }

    @Override
    public List<SysRolePermissionRelation> queryAllByPermissionId(Long permissionId) {
        return sysRolePermissionRelationDao.findAllByPermissionId(permissionId);
    }

    @Override
    public SysRolePermissionRelation queryByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        return sysRolePermissionRelationDao.findByRoleIdAndPermissionId(roleId, permissionId).orElse(null);
    }
}