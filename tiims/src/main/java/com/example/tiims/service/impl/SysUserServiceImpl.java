package com.example.tiims.service.impl;

import org.springframework.stereotype.Service;
import com.example.tiims.dao.SysUserDao;
import com.example.tiims.entity.SysUser;
import com.example.tiims.service.SysUserService;

import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao;

    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long id) {
        return sysUserDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(Long offset, Long limit) {
        return sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        return sysUserDao.save(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        return sysUserDao.saveAndFlush(sysUser);
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
            sysUserDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysUserDao.findById(id).isEmpty();
    }

    @Override
    public SysUser selectByUserName(String userName) {
        return sysUserDao.findByUserName(userName).orElse(null);
    }

}