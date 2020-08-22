package com.example.tiims.dao;

import com.example.tiims.entity.TiStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 学生信息
 *
 * @author qq1962247851
 * @date 2020/7/14 17:05
 **/
public interface TiStudentDao extends JpaRepository<TiStudent, Long> {

    /**
     * 根据班级id分页查询学生
     *
     * @param classId  classId
     * @param pageable pageable
     * @return page
     */
    @Query("select tiStudent from TiStudent tiStudent " +
            "inner join ti_cs_relation as csRelation on csRelation.classId = ?1 " +
            "where tiStudent.id = csRelation.studentId")
    Page<TiStudent> queryAllByClassId(Long classId, Pageable pageable);

}
