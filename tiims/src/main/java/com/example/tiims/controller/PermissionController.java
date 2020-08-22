package com.example.tiims.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;
import com.example.tiims.common.utils.ResultTool;
import com.example.tiims.entity.SysPermission;
import com.example.tiims.service.SysPermissionService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "权限API接口")
@RestController
@RequestMapping(value = "permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PermissionController {
    private final SysPermissionService sysPermissionService;

    public PermissionController(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }

    @ApiOperation(value = "新增权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4010, message = "权限code已存在")
    })
    @PutMapping("insert")
    public JsonResult<?> insert(@RequestBody SysPermission sysPermission) {
        if (sysPermissionService.queryByCode(sysPermission.getCode()) != null) {
            //权限code已存在
            return ResultTool.fail(ResultCode.PERMISSION_CODE_ALREADY_EXIST);
        }
        SysPermission insert = sysPermissionService.insert(sysPermission);
        return ResultTool.success(insert);
    }

    @ApiOperation(value = "查询权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "查询的数据不存在"),
            @ApiResponse(code = 1004, message = "参数缺失")
    })
    @GetMapping("query")
    public JsonResult<?> query(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long offset,
                               @RequestParam(required = false) Long limit) {
        if (id != null) {
            SysPermission sysPermission = sysPermissionService.queryById(id);
            if (sysPermission == null) {
                return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
            } else {
                return ResultTool.success(sysPermission);
            }
        } else {
            if (offset != null && limit != null) {
                List<SysPermission> sysPermissions = sysPermissionService.queryAllByLimit(offset, limit);
                return ResultTool.success(sysPermissions);
            } else {
                return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
            }
        }
    }

    @ApiOperation(value = "删除权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long id) {
        if (sysPermissionService.deleteById(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail();
        }
    }

    @ApiOperation(value = "修改权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysPermission sysPermission) {
        SysPermission update = sysPermissionService.update(sysPermission);
        return ResultTool.success(update);
    }
}
