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
import com.example.tiims.entity.SysRequestPath;
import com.example.tiims.service.SysRequestPathService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "请求路径API接口")
@RestController
@RequestMapping(value = "requestPath", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RequestPathController {
    private final SysRequestPathService requestPathService;

    public RequestPathController(SysRequestPathService requestPathService) {
        this.requestPathService = requestPathService;
    }

    @ApiOperation(value = "新增请求路径")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4009, message = "请求路径url已存在")
    })
    @PutMapping("insert")
    public JsonResult<?> insert(@RequestBody SysRequestPath sysRequestPath) {
        if (requestPathService.queryByUrl(sysRequestPath.getUrl()) != null) {
            //请求路径url已存在
            return ResultTool.fail(ResultCode.REQUEST_PATH_URL_ALREADY_EXIST);
        }
        SysRequestPath insert = requestPathService.insert(sysRequestPath);
        return ResultTool.success(insert);
    }

    @ApiOperation(value = "删除请求路径")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long id) {
        if (requestPathService.deleteById(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail();
        }
    }

    @ApiOperation(value = "修改请求路径")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysRequestPath sysRequestPath) {
        SysRequestPath update = requestPathService.update(sysRequestPath);
        return ResultTool.success(update);
    }

    @ApiOperation(value = "根据id查询请求路径，或者分页查询请求路径，或者查询所有请求路径")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @GetMapping("query")
    public JsonResult<?> query(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long offset,
                               @RequestParam(required = false) Long limit) {
        if (id != null) {
            return ResultTool.success(requestPathService.queryById(id));
        } else {
            List<SysRequestPath> sysRequestPaths;
            if (offset != null && limit != null) {
                sysRequestPaths = requestPathService.queryAllByLimit(offset, limit);
            } else {
                sysRequestPaths = requestPathService.queryAll();
            }
            return ResultTool.success(sysRequestPaths);
        }
    }

}
