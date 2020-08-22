package com.example.tiims.controller;

import com.example.tiims.common.AbstractCrudController;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;
import com.example.tiims.common.utils.ResultTool;
import com.example.tiims.entity.TiClass;
import com.example.tiims.entity.TiStudent;
import com.example.tiims.entity.TiTeacher;
import com.example.tiims.service.TiClassService;
import com.example.tiims.service.TiStudentService;
import com.example.tiims.service.TiTeacherService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级信息控制器
 *
 * @author qq1962247851
 * @date 2020/7/14 17:12
 **/
@Api(description = "班级信息API")
@RestController
@RequestMapping(value = "class", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClassController extends AbstractCrudController<TiClass, Long> {

    private TiClassService classService;
    private TiTeacherService teacherService;
    private TiStudentService studentService;

    public ClassController(TiClassService classService, TiTeacherService teacherService, TiStudentService studentService) {
        this.classService = classService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Override
    public JsonResult<?> insert(@RequestBody TiClass tiClass) {
        return ResultTool.success(classService.insert(tiClass));
    }

    @Override
    public JsonResult<?> delete(Long id) {
        if (classService.delete(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }

    @Override
    public JsonResult<?> update(@RequestBody TiClass tiClass) {
        return ResultTool.success(classService.update(tiClass));
    }

    @Override
    public JsonResult<?> query(Long id, Integer page, Integer size) {
        if (id == null) {
            if (page != null && size != null) {
                return ResultTool.success(classService.queryAll(page, size));
            } else {
                return ResultTool.success(classService.queryAll());
            }
        } else {
            return ResultTool.success(classService.query(id));
        }
    }

    @GetMapping("queryTeachers")
    public JsonResult<?> queryTeachers(Long classId) {
        if (classId != null) {
            return ResultTool.success(classService.queryTeachersById(classId));
        } else {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
    }

    @GetMapping("queryStudents")
    public JsonResult<?> queryStudents(Long classId, Integer page, Integer size) {
        if (classId != null && page != null && size != null) {
            return ResultTool.success(classService.queryStudentsById(classId, page, size));
        } else {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
    }

    @PutMapping("insertTeachers")
    public JsonResult<?> insertTeachers(Long id, @RequestParam List<Long> teachers) {
        if (id == null || teachers.isEmpty()) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        } else {
            List<TiTeacher> tiTeachers = new ArrayList<>();
            teachers.forEach(teacherId -> tiTeachers.add(teacherService.query(teacherId)));
            if (classService.insertTeachers(id, tiTeachers)) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @DeleteMapping("deleteTeachers")
    public JsonResult<?> deleteTeachers(Long id, @RequestParam List<Long> teachers) {
        if (id == null || teachers.isEmpty()) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        } else {
            if (classService.deleteTeachers(id, teachers)) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @PutMapping("insertStudents")
    public JsonResult<?> insertStudents(Long id, @RequestParam List<Long> students) {
        if (id == null || students.isEmpty()) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        } else {
            List<TiStudent> tiStudents = new ArrayList<>();
            students.forEach(studentId -> tiStudents.add(studentService.query(studentId)));
            if (classService.insertStudents(id, tiStudents)) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @DeleteMapping("deleteStudents")
    public JsonResult<?> deleteStudents(Long id, @RequestParam List<Long> students) {
        if (id == null || students.isEmpty()) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        } else {
            if (classService.deleteStudents(id, students)) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @GetMapping("queryByTeacherId")
    public JsonResult<?> queryByTeacherId(Long teacherId) {
        if (teacherId == null) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        } else {
            return ResultTool.success(classService.queryByTeacherId(teacherId));
        }
    }

    @GetMapping("queryByStudentId")
    public JsonResult<?> queryByStudentId(Long studentId) {
        if (studentId == null) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        } else {
            return ResultTool.success(classService.queryByStudentId(studentId));
        }
    }

}
