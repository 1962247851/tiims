package com.example.tiims.service.impl;

import org.springframework.stereotype.Service;
import com.example.tiims.dao.SysUserRoleRelationDao;
import com.example.tiims.entity.SysUserRoleRelation;
import com.example.tiims.service.SysUserRoleRelationService;

import java.util.List;

/**
 * 用户角色关联表(SysUserRoleRelation)表服务实现类
 */
@Service
public class SysUserRoleRelationServiceImpl implements SysUserRoleRelationService {

    private final SysUserRoleRelationDao sysUserRoleRelationDao;

    public SysUserRoleRelationServiceImpl(SysUserRoleRelationDao sysUserRoleRelationDao) {
        this.sysUserRoleRelationDao = sysUserRoleRelationDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRoleRelation queryById(Long id) {
        return sysUserRoleRelationDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUserRoleRelation> queryAllByLimit(Long offset, Long limit) {
        return sysUserRoleRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRoleRelation insert(SysUserRoleRelation sysRequestPathPermissionRelation) {
        return sysUserRoleRelationDao.save(sysRequestPathPermissionRelation);
    }

    /**
     * 修改数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRoleRelation update(SysUserRoleRelation sysRequestPathPermissionRelation) {
        return sysUserRoleRelationDao.saveAndFlush(sysRequestPathPermissionRelation);
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
            sysUserRoleRelationDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysUserRoleRelationDao.findById(id).isEmpty();
    }

    @Override
    public List<SysUserRoleRelation> queryAllByUserId(Long userId) {
        return sysUserRoleRelationDao.findAllByUserId(userId);
    }

    @Override
    public List<SysUserRoleRelation> queryAllByRoleId(Long roleId) {
        return sysUserRoleRelationDao.findAllByRoleId(roleId);
    }

    @Override
    public SysUserRoleRelation queryByUserIdAndRoleId(Long userId, Long roleId) {
        return sysUserRoleRelationDao.findByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public List<SysUserRoleRelation> queryAll() {
        return sysUserRoleRelationDao.findAll();
    }
}