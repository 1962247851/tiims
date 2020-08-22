package com.example.tiims.service.impl;

import com.example.tiims.dao.SysPermissionDao;
import com.example.tiims.entity.SysPermission;
import com.example.tiims.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private final SysPermissionDao sysPermissionDao;

    public SysPermissionServiceImpl(SysPermissionDao sysPermissionDao) {
        this.sysPermissionDao = sysPermissionDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPermission queryById(Long id) {
        return sysPermissionDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysPermission> queryAllByLimit(Long offset, Long limit) {
        return sysPermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysPermission insert(SysPermission sysPermission) {
        return sysPermissionDao.save(sysPermission);
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysPermission update(SysPermission sysPermission) {
        return sysPermissionDao.saveAndFlush(sysPermission);
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
            sysPermissionDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysPermissionDao.findById(id).isEmpty();
    }

    @Override
    public SysPermission queryByCode(String code) {
        return sysPermissionDao.findByCode(code).orElse(null);
    }

}