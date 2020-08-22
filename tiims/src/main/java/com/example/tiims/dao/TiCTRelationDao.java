package com.example.tiims.dao;

import com.example.tiims.entity.TiCTRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author qq1962247851
 * @date 2020/7/15 21:32
 **/
public interface TiCTRelationDao extends JpaRepository<TiCTRelation, Long> {
    /**
     * 根据班级id和教师id查询是否存在关联关系
     *
     * @param classId   班级id
     * @param teacherId 教师id
     * @return class
     */
    Optional<TiCTRelation> queryByClassIdAndTeacherId(Long classId, Long teacherId);

    /**
     * 根据班级id和教师id删除关联关系
     *
     * @param classId   班级id
     * @param teacherId 教师id
     */
    @Transactional
    void deleteByClassIdAndTeacherId(Long classId, Long teacherId);

    /**
     * 根据教师id查询所有关联关系
     *
     * @param teacherId 教师id
     * @return class
     */
    List<TiCTRelation> queryAllByTeacherId(Long teacherId);


}
