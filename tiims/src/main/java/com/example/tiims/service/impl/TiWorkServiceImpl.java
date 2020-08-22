package com.example.tiims.service.impl;

import com.example.tiims.dao.TiWorkDao;
import com.example.tiims.entity.CustomTiWork;
import com.example.tiims.entity.TiWork;
import com.example.tiims.service.TiWorkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 出勤信息服务实现类
 *
 * @author qq1962247851
 * @date 2020/7/14 17:03
 **/
@Service
public class TiWorkServiceImpl implements TiWorkService {

    private TiWorkDao tiWorkDao;

    public TiWorkServiceImpl(TiWorkDao tiWorkDao) {
        this.tiWorkDao = tiWorkDao;
    }

    @Override
    public TiWork insert(TiWork tiWork) {
        return tiWorkDao.save(tiWork);
    }

    @Override
    public @NotNull Boolean delete(Long id) {
        try {
            tiWorkDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return tiWorkDao.findById(id).isEmpty();
    }

    @Override
    public TiWork update(TiWork tiWork) {
        return tiWorkDao.saveAndFlush(tiWork);
    }

    @Override
    public TiWork query(Long id) {
        return tiWorkDao.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer size) {
        Page<TiWork> all = tiWorkDao.findAll(PageRequest.of(page, size));
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
    public List<TiWork> queryAll() {
        return tiWorkDao.findAll();
    }

    @Override
    public List<CustomTiWork> queryAllCustom() {
        return tiWorkDao.queryAll();
    }

    /**
     * 根据日期和班级id查询当天的出勤表
     *
     * @return 自定义出勤表
     */
    @Override
    public Optional<CustomTiWork> queryByClassIdAndDate(Long classId, Date date) {
        return tiWorkDao.queryByClassIdAndDate(classId, date);
    }
}
