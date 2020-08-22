package com.example.tiims.service.impl;

import com.example.tiims.dao.TiTeacherDao;
import com.example.tiims.entity.TiTeacher;
import com.example.tiims.service.TiTeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 师资信息服务实现类
 *
 * @author qq1962247851
 * @date 2020/7/14 17:03
 **/
@Service
public class TiTeacherServiceImpl implements TiTeacherService {

    private TiTeacherDao tiTeacherDao;

    public TiTeacherServiceImpl(TiTeacherDao tiTeacherDao) {
        this.tiTeacherDao = tiTeacherDao;
    }

    @Override
    public TiTeacher insert(TiTeacher tiTeacher) {
        return tiTeacherDao.save(tiTeacher);
    }

    @Override
    public @NotNull Boolean delete(Long id) {
        try {
            tiTeacherDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return tiTeacherDao.findById(id).isEmpty();
    }

    @Override
    public TiTeacher update(TiTeacher tiTeacher) {
        return tiTeacherDao.saveAndFlush(tiTeacher);
    }

    @Override
    public TiTeacher query(Long id) {
        return tiTeacherDao.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer size) {
        Page<TiTeacher> all = tiTeacherDao.findAll(PageRequest.of(page, size));
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
    public List<TiTeacher> queryAll() {
        return tiTeacherDao.findAll();
    }

}
