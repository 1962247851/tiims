package com.example.tiims.dao;

import com.example.tiims.entity.TiCSRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author qq1962247851
 * @date 2020/7/15 21:32
 **/
public interface TiCSRelationDao extends JpaRepository<TiCSRelation, Long> {


    /**
     * 根据班级id和学生id查询关联关系
     *
     * @param classId   班级id
     * @param studentId 学生id
     * @return 关联关系
     */
    Optional<TiCSRelation> queryByClassIdAndStudentId(Long classId, Long studentId);

    /**
     * 根据班级id和学生id删除关联关系
     *
     * @param classId   班级id
     * @param studentId 学生id
     */
    @Transactional
    void deleteByClassIdAndStudentId(Long classId, Long studentId);


    /**
     * 根据学生id查询所有关联关系
     *
     * @param studentId 学生id
     * @return class
     */
    List<TiCSRelation> queryAllByStudentId(Long studentId);
}
