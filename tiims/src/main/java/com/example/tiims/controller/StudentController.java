package com.example.tiims.controller;

import com.example.tiims.common.AbstractCrudController;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;
import com.example.tiims.common.utils.ResultTool;
import com.example.tiims.entity.TiStudent;
import com.example.tiims.service.TiStudentService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生信息控制器
 *
 * @author qq1962247851
 * @date 2020/7/14 17:12
 **/
@Api(description = "学生信息API")
@RestController
@RequestMapping(value = "student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController extends AbstractCrudController<TiStudent, Long> {

    private TiStudentService studentService;

    public StudentController(TiStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public JsonResult<?> insert(@RequestBody TiStudent tiStudent) {
        return ResultTool.success(studentService.insert(tiStudent));
    }

    @Override
    public JsonResult<?> delete(Long id) {
        if (studentService.delete(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }

    @Override
    public JsonResult<?> update(@RequestBody TiStudent tiStudent) {
        return ResultTool.success(studentService.update(tiStudent));
    }

    @Override
    public JsonResult<?> query(Long id, Integer page, Integer size) {
        if (id == null) {
            if (page != null && size != null) {
                return ResultTool.success(studentService.queryAll(page, size));
            } else {
                return ResultTool.success(studentService.queryAll());
            }
        } else {
            return ResultTool.success(studentService.query(id));
        }
    }

}
