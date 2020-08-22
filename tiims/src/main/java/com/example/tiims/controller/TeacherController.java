package com.example.tiims.controller;

import com.example.tiims.common.AbstractCrudController;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;
import com.example.tiims.common.utils.ResultTool;
import com.example.tiims.dao.TiCTRelationDao;
import com.example.tiims.entity.CustomTiTeacher;
import com.example.tiims.entity.TiClass;
import com.example.tiims.entity.TiTeacher;
import com.example.tiims.service.TiClassService;
import com.example.tiims.service.TiTeacherService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 师资信息控制器
 *
 * @author qq1962247851
 * @date 2020/7/14 17:12
 **/
@Api(description = "师资信息API")
@RestController
@RequestMapping(value = "teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController extends AbstractCrudController<TiTeacher, Long> {

    private TiTeacherService teacherService;
    private TiClassService classService;
    private TiCTRelationDao ctRelationDao;

    public TeacherController(TiTeacherService teacherService, TiClassService classService, TiCTRelationDao ctRelationDao) {
        this.teacherService = teacherService;
        this.classService = classService;
        this.ctRelationDao = ctRelationDao;
    }

    @Override
    public JsonResult<?> insert(@RequestBody TiTeacher tiTeacher) {
        TiTeacher insert = teacherService.insert(tiTeacher);
        List<TiClass> classList = new ArrayList<>();
        ctRelationDao.queryAllByTeacherId(tiTeacher.getId()).forEach(ctRelation -> classList.add(classService.query(ctRelation.getClassId())));
        return ResultTool.success(new CustomTiTeacher(insert, classList));
    }

    @Override
    public JsonResult<?> delete(Long id) {
        if (teacherService.delete(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }

    @Override
    public JsonResult<?> update(@RequestBody TiTeacher tiTeacher) {
        TiTeacher update = teacherService.update(tiTeacher);
        List<TiClass> classList = new ArrayList<>();
        ctRelationDao.queryAllByTeacherId(tiTeacher.getId()).forEach(ctRelation -> classList.add(classService.query(ctRelation.getClassId())));
        return ResultTool.success(new CustomTiTeacher(update, classList));
    }

    @Override
    public JsonResult<?> query(Long id, Integer page, Integer size) {
        if (id == null) {
            if (page != null && size != null) {
                return ResultTool.success(teacherService.queryAll(page, size));
            } else {
                return ResultTool.success(teacherService.queryAll());
            }
        } else {
            return ResultTool.success(teacherService.query(id));
        }
    }

}
