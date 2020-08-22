package com.example.tiims.service.impl;

import com.example.tiims.dao.*;
import com.example.tiims.entity.*;
import com.example.tiims.service.TiClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 师资信息服务实现类
 *
 * @author qq1962247851
 * @date 2020/7/14 17:03
 **/
@Service
public class TiClassServiceImpl implements TiClassService {

    private TiClassDao tiClassDao;
    private TiStudentDao tiStudentDao;
    private TiTeacherDao tiTeacherDao;
    private TiCTRelationDao ctRelationDao;
    private TiCSRelationDao csRelationDao;

    public TiClassServiceImpl(TiClassDao tiClassDao, TiStudentDao tiStudentDao, TiTeacherDao tiTeacherDao, TiCTRelationDao ctRelationDao, TiCSRelationDao csRelationDao) {
        this.tiClassDao = tiClassDao;
        this.tiStudentDao = tiStudentDao;
        this.tiTeacherDao = tiTeacherDao;
        this.ctRelationDao = ctRelationDao;
        this.csRelationDao = csRelationDao;
    }

    @Override
    public TiClass insert(TiClass tiClass) {
        return tiClassDao.save(tiClass);
    }

    @Override
    public @NotNull Boolean delete(Long id) {
        try {
            tiClassDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return tiClassDao.findById(id).isEmpty();
    }

    @Override
    public TiClass update(TiClass tiClass) {
        return tiClassDao.saveAndFlush(tiClass);
    }

    @Override
    public TiClass query(Long id) {
        return tiClassDao.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer size) {
        Page<TiClass> all = tiClassDao.findAll(PageRequest.of(page, size));
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
    public List<TiClass> queryAll() {
        return tiClassDao.findAll();
    }

    /**
     * 根据班级id查询班级中的班主任、助教信息
     *
     * @param id 班级id
     * @return list
     */
    @Override
    public List<TiTeacher> queryTeachersById(Long id) {
        return tiTeacherDao.queryAllByClassId(id);
    }

    /**
     * 根据班级id分页查询班级中的学生
     *
     * @param id   班级id
     * @param page 页数（从0开始）
     * @param size 每页的大小
     * @return map{totalPages,students}
     */
    @Override
    public Map<String, Object> queryStudentsById(Long id, Integer page, Integer size) {
        Page<TiStudent> all = tiStudentDao.queryAllByClassId(id, PageRequest.of(page, size, Sort.by("name").descending()));
        Map<String, Object> map = new HashMap<>(2);
        map.put("totalPages", all.getTotalPages());
        map.put("students", all.getContent());
        return map;
    }

    /**
     * 批量导入班级的师资信息，教师必须已存在
     *
     * @param id       班级id
     * @param teachers 教师/助教
     * @return boolean
     */
    @Override
    public Boolean insertTeachers(Long id, List<TiTeacher> teachers) {
        try {
            teachers.forEach(tiTeacher -> {
                TiCTRelation relation = new TiCTRelation(id, tiTeacher.getId());
                if (tiTeacher.getDegree() == 0) {
                    //班主任只能有一个
                    if (ctRelationDao.queryByClassIdAndTeacherId(id, tiTeacher.getId()).isEmpty()) {
                        ctRelationDao.save(relation);
                    }
                } else {
                    ctRelationDao.save(relation);
                }
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 批量删除教师/助教
     *
     * @param id       班级id
     * @param teachers 教师/助教
     * @return boolean
     */
    @Override
    public Boolean deleteTeachers(Long id, List<Long> teachers) {
        try {
            teachers.forEach(teacherId -> ctRelationDao.deleteByClassIdAndTeacherId(id, teacherId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 批量导入学生
     *
     * @param id       班级id
     * @param students 学生
     * @return boolean
     */
    @Override
    public Boolean insertStudents(Long id, List<TiStudent> students) {
        try {
            students.forEach(tiStudent -> {
                Optional<TiCSRelation> optional = csRelationDao.queryByClassIdAndStudentId(id, tiStudent.getId());
                if (optional.isPresent()) {
                    TiCSRelation relation = optional.get();
                    relation.setClassId(id);
                    csRelationDao.saveAndFlush(relation);
                } else {
                    csRelationDao.save(new TiCSRelation(id, tiStudent.getId()));
                }
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 批量删除学生
     *
     * @param id       班级id
     * @param students 学生
     * @return boolean
     */
    @Override
    public Boolean deleteStudents(Long id, List<Long> students) {
        try {
            students.forEach(studentId -> csRelationDao.deleteByClassIdAndStudentId(id, studentId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根据教师id分页查询所有相关的班级信息
     *
     * @param teacherId 教师id
     * @return classList
     */
    @Override
    public List<TiClass> queryByTeacherId(Long teacherId) {
        List<TiClass> classList = new ArrayList<>();
        ctRelationDao.queryAllByTeacherId(teacherId).forEach(ctRelation -> tiClassDao.findById(ctRelation.getClassId()).ifPresent(classList::add));
        return classList;
    }

    /**
     * 根据学生id查询所有相关的班级信息
     *
     * @param studentId 学生id
     * @return classList
     */
    @Override
    public List<TiClass> queryByStudentId(Long studentId) {
        List<TiClass> classList = new ArrayList<>();
        csRelationDao.queryAllByStudentId(studentId).forEach(csRelation -> tiClassDao.findById(csRelation.getClassId()).ifPresent(classList::add));
        return classList;
    }

}
