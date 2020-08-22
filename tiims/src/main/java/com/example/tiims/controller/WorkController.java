package com.example.tiims.controller;

import com.example.tiims.common.AbstractCrudController;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;
import com.example.tiims.common.utils.Constant;
import com.example.tiims.common.utils.ResultTool;
import com.example.tiims.entity.CustomTiWork;
import com.example.tiims.entity.TiTeacher;
import com.example.tiims.entity.TiWork;
import com.example.tiims.service.TiClassService;
import com.example.tiims.service.TiTeacherService;
import com.example.tiims.service.TiWorkService;
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
@Api(description = "出勤信息API")
@RestController
@RequestMapping(value = "work", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WorkController extends AbstractCrudController<TiWork, Long> {

    private TiWorkService workService;
    private TiClassService classService;
    private TiTeacherService teacherService;

    public WorkController(TiWorkService workService, TiClassService classService, TiTeacherService teacherService) {
        this.workService = workService;
        this.classService = classService;
        this.teacherService = teacherService;
    }

    @Override
    public JsonResult<?> insert(@RequestBody TiWork tiWork) {
        JsonResult<?> x = checkValid(tiWork);
        if (x != null) {
            return x;
        } else {
            if (workService.queryByClassIdAndDate(tiWork.getClassId(), tiWork.getDate()).isEmpty()) {
                TiWork insert = workService.insert(tiWork);
                return ResultTool.success(new CustomTiWork(insert.getId(), insert.getDate(), classService.query(insert.getClassId()),
                        teacherService.query(insert.getTeacherId()), tiWork.getNumber(), tiWork.getUpdateTimestamp()));
            } else {
                return ResultTool.fail(ResultCode.DATA_ALREADY_EXIST);
            }
        }
    }

    @Override
    public JsonResult<?> delete(Long id) {
        if (workService.delete(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }

    @Override
    public JsonResult<?> update(@RequestBody TiWork tiWork) {
        JsonResult<?> x = checkValid(tiWork);
        if (x != null) {
            return x;
        } else {
            TiWork update = workService.update(tiWork);
            return ResultTool.success(new CustomTiWork(update.getId(), update.getDate(), classService.query(update.getClassId()),
                    teacherService.query(update.getTeacherId()), tiWork.getNumber(), tiWork.getUpdateTimestamp()));

        }
    }

    @Override
    public JsonResult<?> query(Long id, Integer page, Integer size) {
        if (id == null) {
            if (page != null && size != null) {
                return ResultTool.success(workService.queryAll(page, size));
            } else {
                return ResultTool.success(workService.queryAllCustom());
            }
        } else {
            return ResultTool.success(workService.query(id));
        }
    }

    private JsonResult<?> checkValid(TiWork tiWork) {
        TiTeacher tiTeacher = teacherService.query(tiWork.getTeacherId());
        if (classService.query(tiWork.getClassId()) == null) {
            return ResultTool.fail(ResultCode.CLASS_NOT_EXIST);
        } else if (tiTeacher == null) {
            return ResultTool.fail(ResultCode.ASSISTANCE_TEACHER_NOT_EXIST);
        } else if (tiTeacher.getDegree() != Constant.ASSISTANCE_TEACHER_INDEX) {
            return ResultTool.fail(ResultCode.TEACHER_NOT_ASSISTANCE);
        } else {
            return null;
        }
    }

}
