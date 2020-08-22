package com.example.tiims.service;

import com.example.tiims.common.CrudService;
import com.example.tiims.entity.CustomTiWork;
import com.example.tiims.entity.TiWork;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 出勤服务类
 *
 * @author qq1962247851
 * @date 2020/7/14 17:01
 **/
public interface TiWorkService extends CrudService<TiWork, Long> {

    /**
     * 查询返回自定义列表
     *
     * @return 自定义列表
     */
    List<CustomTiWork> queryAllCustom();

    /**
     * 根据日期和班级id查询当天的出勤表
     *
     * @param classId 班级id
     * @param date    日期
     * @return 自定义出勤表
     */
    Optional<CustomTiWork> queryByClassIdAndDate(Long classId, Date date);

}
