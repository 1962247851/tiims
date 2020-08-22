package com.example.tiims.service.impl;

import org.springframework.stereotype.Service;
import com.example.tiims.dao.SysRoleDao;
import com.example.tiims.entity.SysRole;
import com.example.tiims.service.SysRoleService;

import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleDao sysRoleDao;

    public SysRoleServiceImpl(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Long id) {
        return sysRoleDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRole> queryAllByLimit(Long offset, Long limit) {
        return sysRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole insert(SysRole sysUser) {
        return sysRoleDao.save(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole update(SysRole sysUser) {
        return sysRoleDao.saveAndFlush(sysUser);
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
            sysRoleDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysRoleDao.findById(id).isEmpty();
    }

    @Override
    public SysRole queryByRoleName(String roleName) {
        return sysRoleDao.findByName(roleName).orElse(null);
    }

    @Override
    public SysRole queryByRoleCode(String roleCode) {
        return sysRoleDao.findByCode(roleCode).orElse(null);
    }

    @Override
    public List<SysRole> queryAll() {
        return sysRoleDao.findAll();
    }

}