package com.example.tiims.dao;

import com.example.tiims.entity.CustomTiWork;
import com.example.tiims.entity.TiWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 出勤表
 *
 * @author qq1962247851
 * @date 2020/7/14 17:05
 **/
public interface TiWorkDao extends JpaRepository<TiWork, Long> {

    /**
     * 查询所有出勤表
     *
     * @return 返回自定义TiWork列表
     */
    @Query("select new com.example.tiims.entity.CustomTiWork(tiWork.id,tiWork.date,tiClass,teacher,tiWork.number,tiWork.updateTimestamp) " +
            "from TiWork tiWork " +
            "inner join TiTeacher as teacher on teacher.id = tiWork.teacherId " +
            "inner join TiClass as tiClass on tiClass.id = tiWork.classId")
    List<CustomTiWork> queryAll();

    /**
     * 查询所有出勤表
     *
     * @return 返回自定义TiWork列表
     */
    @Query("select new com.example.tiims.entity.CustomTiWork(tiWork.id,tiWork.date,tiClass,teacher,tiWork.number,tiWork.updateTimestamp) " +
            "from TiWork tiWork " +
            "inner join TiTeacher as teacher on teacher.id = tiWork.teacherId " +
            "inner join TiClass as tiClass on tiClass.id = tiWork.classId " +
            "where tiWork.classId = ?1 and tiWork.date = ?2")
    Optional<CustomTiWork> queryByClassIdAndDate(Long classId, Date date);

}
