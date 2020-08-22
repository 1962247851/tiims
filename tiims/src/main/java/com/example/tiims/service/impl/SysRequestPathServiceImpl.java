package com.example.tiims.service.impl;

import org.springframework.stereotype.Service;
import com.example.tiims.dao.SysRequestPathDao;
import com.example.tiims.entity.SysRequestPath;
import com.example.tiims.service.SysRequestPathService;

import java.util.List;

/**
 * 请求路径(SysRequestPath)表服务实现类
 */
@Service
public class SysRequestPathServiceImpl implements SysRequestPathService {
    private final SysRequestPathDao sysRequestPathDao;

    public SysRequestPathServiceImpl(SysRequestPathDao sysRequestPathDao) {
        this.sysRequestPathDao = sysRequestPathDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRequestPath queryById(Long id) {
        return sysRequestPathDao.findById(id).orElse(null);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRequestPath> queryAllByLimit(Long offset, Long limit) {
        return sysRequestPathDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRequestPath 实例对象
     * @return 实例对象
     */
    @Override
    public SysRequestPath insert(SysRequestPath sysRequestPath) {
        return sysRequestPathDao.save(sysRequestPath);
    }

    /**
     * 修改数据
     *
     * @param sysRequestPath 实例对象
     * @return 实例对象
     */
    @Override
    public SysRequestPath update(SysRequestPath sysRequestPath) {
        return sysRequestPathDao.saveAndFlush(sysRequestPath);
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
            sysRequestPathDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return sysRequestPathDao.findById(id).isEmpty();
    }

    @Override
    public SysRequestPath queryByUrl(String url) {
        return sysRequestPathDao.findByUrl(url).orElse(null);
    }

    @Override
    public List<SysRequestPath> queryAll() {
        return sysRequestPathDao.findAll();
    }
}