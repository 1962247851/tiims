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
import com.example.tiims.entity.SysRolePermissionRelation;
import com.example.tiims.service.SysRolePermissionRelationService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "角色权限关联关系API接口")
@RestController
@RequestMapping(value = "rolePermissionRelation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RolePermissionRelationController {

    private final SysRolePermissionRelationService rolePermissionRelationService;

    public RolePermissionRelationController(SysRolePermissionRelationService rolePermissionRelationService) {
        this.rolePermissionRelationService = rolePermissionRelationService;
    }

    @ApiOperation(value = "给角色添加权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4006, message = "关系已存在")
    })
    @PutMapping("insert")
    public JsonResult<?> insert(@RequestParam Long roleId, @RequestParam Long permissionId) {
        if (rolePermissionRelationService.queryByRoleIdAndPermissionId(roleId, permissionId) == null) {
            rolePermissionRelationService.insert(new SysRolePermissionRelation(roleId, permissionId));
            return ResultTool.success();
        } else {
            //关系已存在
            return ResultTool.fail(ResultCode.RELATION_ALREADY_EXIST);
        }
    }

    @ApiOperation(value = "删除角色的某个权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long roleId, @RequestParam Long permissionId) {
        SysRolePermissionRelation sysRolePermissionRelation = rolePermissionRelationService.queryByRoleIdAndPermissionId(roleId, permissionId);
        if (sysRolePermissionRelation == null) {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        } else {
            if (rolePermissionRelationService.deleteById(sysRolePermissionRelation.getId())) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @ApiOperation(value = "更新角色权限关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysRolePermissionRelation sysRolePermissionRelation) {
        if (rolePermissionRelationService.queryById(sysRolePermissionRelation.getId()) != null) {
            return ResultTool.success(rolePermissionRelationService.update(sysRolePermissionRelation));
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }

    @ApiOperation(value = "查询角色权限关联关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在"),
            @ApiResponse(code = 1004, message = "参数缺失")
    })
    @GetMapping("query")
    public JsonResult<?> query(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long offset,
                               @RequestParam(required = false) Long limit) {
        if (id != null) {
            SysRolePermissionRelation sysRolePermissionRelation = rolePermissionRelationService.queryById(id);
            return ResultTool.success(sysRolePermissionRelation);
        } else {
            if (offset != null && limit != null) {
                List<SysRolePermissionRelation> sysRolePermissionRelations = rolePermissionRelationService.queryAllByLimit(offset, limit);
                return ResultTool.success(sysRolePermissionRelations);
            } else {
                return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
            }
        }
    }

}
