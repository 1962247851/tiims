package com.example.tiims.service;

import com.example.tiims.common.CrudService;
import com.example.tiims.entity.TiClass;
import com.example.tiims.entity.TiStudent;
import com.example.tiims.entity.TiTeacher;

import java.util.List;
import java.util.Map;

/**
 * 班级信息服务类
 *
 * @author qq1962247851
 * @date 2020/7/14 17:01
 **/
public interface TiClassService extends CrudService<TiClass, Long> {

    /**
     * 根据班级id查询班级中的班主任、助教信息
     *
     * @param id 班级id
     * @return list
     */
    List<TiTeacher> queryTeachersById(Long id);

    /**
     * 根据班级id分页查询班级中的学生
     *
     * @param id   班级id
     * @param page 页数（从0开始）
     * @param size 每页的大小
     * @return map{totalPages,students}
     */
    Map<String, Object> queryStudentsById(Long id, Integer page, Integer size);

    /**
     * 批量导入教师/助教
     *
     * @param id       班级id
     * @param teachers 教师/助教
     * @return boolean
     */
    Boolean insertTeachers(Long id, List<TiTeacher> teachers);

    /**
     * 批量删除教师/助教
     *
     * @param id       班级id
     * @param teachers 教师/助教
     * @return boolean
     */
    Boolean deleteTeachers(Long id, List<Long> teachers);

    /**
     * 批量导入学生
     *
     * @param id       班级id
     * @param students 学生
     * @return boolean
     */
    Boolean insertStudents(Long id, List<TiStudent> students);

    /**
     * 批量删除学生
     *
     * @param id       班级id
     * @param students 学生
     * @return boolean
     */
    Boolean deleteStudents(Long id, List<Long> students);

    /**
     * 根据教师id查询所有相关的班级信息
     *
     * @param teacherId 教师id
     * @return classList
     */
    List<TiClass> queryByTeacherId(Long teacherId);

    /**
     * 根据学生id查询所有相关的班级信息
     *
     * @param studentId 学生id
     * @return classList
     */
    List<TiClass> queryByStudentId(Long studentId);

}
