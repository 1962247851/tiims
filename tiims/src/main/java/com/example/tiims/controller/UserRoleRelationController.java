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
import com.example.tiims.entity.SysUserRoleRelation;
import com.example.tiims.service.SysUserRoleRelationService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "用户角色关联关系API接口")
@RestController
@RequestMapping(value = "userRoleRelation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserRoleRelationController {

    private final SysUserRoleRelationService userRoleRelationService;

    public UserRoleRelationController(SysUserRoleRelationService userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
    }

    @ApiOperation(value = "给用户添加角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4006, message = "关系已存在")
    })
    @PutMapping("insert")
    public JsonResult<?> insert(@RequestParam Long userId, @RequestParam Long roleId) {
        if (userRoleRelationService.queryByUserIdAndRoleId(userId, roleId) == null) {
            userRoleRelationService.insert(new SysUserRoleRelation(userId, roleId));
            return ResultTool.success();
        } else {
            //关系已存在
            return ResultTool.fail(ResultCode.RELATION_ALREADY_EXIST);
        }
    }

    @ApiOperation(value = "删除用户的某个角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long userId, @RequestParam Long roleId) {
        SysUserRoleRelation userRoleRelation = userRoleRelationService.queryByUserIdAndRoleId(userId, roleId);
        if (userRoleRelation == null) {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        } else {
            if (userRoleRelationService.deleteById(userRoleRelation.getId())) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @ApiOperation(value = "更新用户角色关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysUserRoleRelation sysUserRoleRelation) {
        if (userRoleRelationService.queryById(sysUserRoleRelation.getId()) != null) {
            return ResultTool.success(userRoleRelationService.update(sysUserRoleRelation));
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }

    @ApiOperation(value = "根据userId或roleId查询用户角色关联关系，至少传入一个参数")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 1004, message = "参数缺失")
    })
    @GetMapping("queryByUserIdAndRoleId")
    public JsonResult<?> query(@RequestParam(required = false) Long userId,
                               @RequestParam(required = false) Long roleId) {
        if (userId != null && roleId != null) {
            return ResultTool.success(userRoleRelationService.queryByUserIdAndRoleId(userId, roleId));
        } else {
            if (userId != null) {
                return ResultTool.success(userRoleRelationService.queryAllByUserId(userId));
            } else if (roleId != null) {
                return ResultTool.success(userRoleRelationService.queryAllByRoleId(roleId));
            } else {
                return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
            }
        }

    }

    @ApiOperation(value = "根据id查询用户角色关联关系，或者分页查询所有关系，或者查询所有关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @GetMapping("query")
    public JsonResult<?> query(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long offset,
                               @RequestParam(required = false) Long limit) {
        if (id != null) {
            return ResultTool.success(userRoleRelationService.queryById(id));
        } else {
            List<SysUserRoleRelation> sysUserRoleRelations;
            if (offset != null && limit != null) {
                sysUserRoleRelations = userRoleRelationService.queryAllByLimit(offset, limit);
            } else {
                sysUserRoleRelations = userRoleRelationService.queryAll();
            }
            return ResultTool.success(sysUserRoleRelations);
        }
    }

}
