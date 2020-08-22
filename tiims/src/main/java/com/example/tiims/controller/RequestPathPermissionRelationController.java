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
import com.example.tiims.entity.SysRequestPathPermissionRelation;
import com.example.tiims.service.SysRequestPathPermissionRelationService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "请求路径权限关联关系API接口")
@RestController
@RequestMapping(value = "requestPathPermissionRelation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RequestPathPermissionRelationController {

    private final SysRequestPathPermissionRelationService requestPathPermissionRelationService;

    public RequestPathPermissionRelationController(SysRequestPathPermissionRelationService requestPathPermissionRelationService) {
        this.requestPathPermissionRelationService = requestPathPermissionRelationService;
    }

    @ApiOperation(value = "给路径添加权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4006, message = "关系已存在")
    })
    @PutMapping("insert")
    public JsonResult<?> insert(@RequestParam Long requestPathId, @RequestParam Long permissionId) {
        if (requestPathPermissionRelationService.queryByRequestPathIdAndPermissionId(requestPathId, permissionId) == null) {
            requestPathPermissionRelationService.insert(new SysRequestPathPermissionRelation(requestPathId, permissionId));
            return ResultTool.success();
        } else {
            //关系已存在
            return ResultTool.fail(ResultCode.RELATION_ALREADY_EXIST);
        }
    }

    @ApiOperation(value = "删除路径的某个权限")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long requestPathId, @RequestParam Long permissionId) {
        SysRequestPathPermissionRelation sysRequestPathPermissionRelation = requestPathPermissionRelationService.queryByRequestPathIdAndPermissionId(requestPathId, permissionId);
        if (sysRequestPathPermissionRelation == null) {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        } else {
            if (requestPathPermissionRelationService.deleteById(sysRequestPathPermissionRelation.getId())) {
                return ResultTool.success();
            } else {
                return ResultTool.fail();
            }
        }
    }

    @ApiOperation(value = "更新路径权限关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4001, message = "数据不存在")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysRequestPathPermissionRelation sysRequestPathPermissionRelation) {
        if (requestPathPermissionRelationService.queryById(sysRequestPathPermissionRelation.getId()) != null) {
            return ResultTool.success(requestPathPermissionRelationService.update(sysRequestPathPermissionRelation));
        } else {
            return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
        }
    }


    @ApiOperation(value = "查询路径权限关联关系")
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
            SysRequestPathPermissionRelation sysRequestPathPermissionRelation = requestPathPermissionRelationService.queryById(id);
            if (sysRequestPathPermissionRelation == null) {
                return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
            } else {
                return ResultTool.success(sysRequestPathPermissionRelation);
            }
        } else {
            if (offset != null && limit != null) {
                List<SysRequestPathPermissionRelation> sysRequestPathPermissionRelations = requestPathPermissionRelationService.queryAllByLimit(offset, limit);
                return ResultTool.success(sysRequestPathPermissionRelations);
            } else {
                return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
            }
        }
    }

}
