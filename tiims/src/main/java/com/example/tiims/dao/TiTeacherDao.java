package com.example.tiims.dao;

import com.example.tiims.entity.TiTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 师资信息
 *
 * @author qq1962247851
 * @date 2020/7/14 17:05
 **/
public interface TiTeacherDao extends JpaRepository<TiTeacher, Long> {

    /**
     * 根据班级id查询所有教师/助教
     *
     * @param classId classId
     * @return list
     */
    @Query("select tiTeacher from TiTeacher tiTeacher " +
            "inner join ti_ct_relation as ctRelation on ctRelation.classId = ?1 " +
            "where tiTeacher.id = ctRelation.teacherId")
    List<TiTeacher> queryAllByClassId(Long classId);

}
