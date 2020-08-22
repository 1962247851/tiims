package com.example.tiims.service.impl;

import org.springframework.stereotype.Service;
import com.example.tiims.dao.SysRequestPathPermissionRelationDao;
import com.example.tiims.entity.SysRequestPathPermissionRelation;
import com.example.tiims.service.SysRequestPathPermissionRelationService;

import java.util.List;

/**
 * 路径权限关联表(SysRequestPathPermissionRelation)表服务实现类
 */
@Service
public class SysRequestPathPermissionRelationServiceImpl implements SysRequestPathPermissionRelationService {

    private final SysRequestPathPermissionRelationDao sysRequestPathPermissionRelationDao;

    public SysRequestPathPermissionRelationServiceImpl(SysRequestPathPermissionRelationDao sysRequestPathPermissionRelationDao) {
        this.sysRequestPathPermissionRelationDao = sysRequestPathPermissionRelationDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRequestPathPermissionRelation queryById(Long id) {
        return sysRequestPathPermissionRelationDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRequestPathPermissionRelation> queryAllByLimit(Long offset, Long limit) {
        return sysRequestPathPermissionRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysRequestPathPermissionRelation insert(SysRequestPathPermissionRelation sysRequestPathPermissionRelation) {
        return sysRequestPathPermissionRelationDao.save(sysRequestPathPermissionRelation);
    }

    /**
     * 修改数据
     *
     * @param sysRequestPathPermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysRequestPathPermissionRelation update(SysRequestPathPermissionRelation sysRequestPathPermissionRelation) {
        return sysRequestPathPermissionRelationDao.saveAndFlush(sysRequestPathPermissionRelation);
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
            sysRequestPathPermissionRelationDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysRequestPathPermissionRelationDao.findById(id).isEmpty();
    }

    @Override
    public List<SysRequestPathPermissionRelation> queryListByUrlId(Long urlId) {
        return sysRequestPathPermissionRelationDao.findAllByRequestPathId(urlId);
    }

    @Override
    public List<SysRequestPathPermissionRelation> queryListByPermissionId(Long permissionId) {
        return sysRequestPathPermissionRelationDao.findAllByPermissionId(permissionId);
    }

    @Override
    public SysRequestPathPermissionRelation queryByRequestPathIdAndPermissionId(Long requestPathId, Long permissionId) {
        return sysRequestPathPermissionRelationDao.findByRequestPathIdAndPermissionId(requestPathId, permissionId).orElse(null);
    }
}