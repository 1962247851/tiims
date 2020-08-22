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
import com.example.tiims.entity.SysRole;
import com.example.tiims.service.SysRoleService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "角色API接口")
@RestController
@RequestMapping(value = "role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {
    private final SysRoleService roleService;

    public RoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "新增角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4003, message = "角色code已存在"),
            @ApiResponse(code = 4004, message = "角色名已存在")
    })
    @PutMapping("insert")
    public JsonResult<?> insert(@RequestBody SysRole role) {
        if (roleService.queryByRoleCode(role.getCode()) != null) {
            //角色code已存在
            return ResultTool.fail(ResultCode.ROLE_CODE_ALREADY_EXIST);
        }
        if (roleService.queryByRoleName(role.getName()) != null) {
            //角色名已存在
            return ResultTool.fail(ResultCode.ROLE_NAME_ALREADY_EXIST);
        }
        SysRole insert = roleService.insert(role);
        return ResultTool.success(insert);
    }

    @ApiOperation(value = "根据id查询角色，或者分页查询角色，或者查询所有角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @GetMapping("query")
    public JsonResult<?> query(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long offset,
                               @RequestParam(required = false) Long limit) {
        if (id != null) {
            return ResultTool.success(roleService.queryById(id));
        } else {
            List<SysRole> sysRoles;
            if (offset != null && limit != null) {
                sysRoles = roleService.queryAllByLimit(offset, limit);
            } else {
                sysRoles = roleService.queryAll();
            }
            return ResultTool.success(sysRoles);
        }
    }

    @ApiOperation(value = "删除角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long id) {
        if (roleService.deleteById(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail();
        }
    }

    @ApiOperation(value = "修改角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysRole role) {
        SysRole update = roleService.update(role);
        return ResultTool.success(update);
    }
}
