package com.example.tiims.service.impl;

import com.example.tiims.dao.TiStudentDao;
import com.example.tiims.entity.TiStudent;
import com.example.tiims.service.TiStudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生信息服务实现类
 *
 * @author qq1962247851
 * @date 2020/7/14 17:03
 **/
@Service
public class TiStudentServiceImpl implements TiStudentService {

    private TiStudentDao tiStudentDao;

    public TiStudentServiceImpl(TiStudentDao tiStudentDao) {
        this.tiStudentDao = tiStudentDao;
    }

    @Override
    public TiStudent insert(TiStudent tiStudent) {
        return tiStudentDao.save(tiStudent);
    }

    @Override
    public @NotNull Boolean delete(Long id) {
        try {
            tiStudentDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return tiStudentDao.findById(id).isEmpty();
    }

    @Override
    public TiStudent update(TiStudent tiStudent) {
        return tiStudentDao.saveAndFlush(tiStudent);
    }

    @Override
    public TiStudent query(Long id) {
        return tiStudentDao.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer size) {
        Page<TiStudent> all = tiStudentDao.findAll(PageRequest.of(page, size));
        Map<String, Object> map = new HashMap<>(2);
        map.put("data", all.toList());
        map.put("totalPages", all.getTotalPages());
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<TiStudent> queryAll() {
        return tiStudentDao.findAll();
    }

}
