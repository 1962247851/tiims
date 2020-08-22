package com.example.tiims.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;
import com.example.tiims.common.utils.ResultTool;
import com.example.tiims.entity.SysUser;
import com.example.tiims.entity.SysUserRoleRelation;
import com.example.tiims.service.SysUserRoleRelationService;
import com.example.tiims.service.SysUserService;

import java.util.List;

/**
 * @author qq1962247851
 */
@Api(description = "用户API接口")
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    private final SysUserService userService;
    private final SysUserRoleRelationService userRoleRelationService;

    public UserController(SysUserService userService, SysUserRoleRelationService userRoleRelationService) {
        this.userService = userService;
        this.userRoleRelationService = userRoleRelationService;
    }

    @ApiOperation(value = "用户注册")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 4002, message = "用户名已存在")
    })
    @PutMapping("register")
    public JsonResult<?> register(@RequestBody SysUser user) {
        if (userService.selectByUserName(user.getUserName()) == null) {
            String realPassword = user.getPassword();
            user.setCreateTime(System.currentTimeMillis());
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            SysUser insert = userService.insert(user);
            SysUser copy = JSONObject.parseObject(JSONObject.toJSONString(insert), SysUser.class);
            copy.setPassword(realPassword);
            userRoleRelationService.insert(new SysUserRoleRelation(insert.getId(), 1L));
            return ResultTool.success(copy);
        } else {
            //用户名已存在
            return ResultTool.fail(ResultCode.USER_NAME_ALREADY_EXIST);
        }
    }

    @ApiOperation(value = "登录", notes = "需要验证码")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 2003, message = "密码错误"),
            @ApiResponse(code = 2007, message = "账号不存在"),
            @ApiResponse(code = 4013, message = "验证码失效"),
            @ApiResponse(code = 4014, message = "验证码错误"),
            @ApiResponse(code = 1004, message = "参数缺失")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "uuid", value = "获取验证码图片后响应中的uuid，原封不动传过来就行"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    @PostMapping("login")
    private JsonResult<?> login(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String uuid, @RequestParam(required = false) String code) {
        return ResultTool.success(new SysUser());
    }

    @ApiOperation(value = "登出")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @GetMapping("logout")
    private JsonResult<?> logout() {
        return ResultTool.success();
    }

    @ApiOperation(value = "查询用户")
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
            SysUser user = userService.queryById(id);
            if (user == null) {
                return ResultTool.fail(ResultCode.DATA_NOT_EXIST);
            } else {
                return ResultTool.success(user);
            }
        } else {
            if (offset != null && limit != null) {
                List<SysUser> sysUsers = userService.queryAllByLimit(offset, limit);
                return ResultTool.success(sysUsers);
            } else {
                return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
            }
        }
    }

    @ApiOperation(value = "删除用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败")
    })
    @DeleteMapping("delete")
    public JsonResult<?> delete(@RequestParam Long id) {
        if (userService.deleteById(id)) {
            return ResultTool.success();
        } else {
            return ResultTool.fail();
        }
    }

    @ApiOperation(value = "修改用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 2003, message = "密码错误"),
            @ApiResponse(code = 4002, message = "用户名已存在")
    })
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody SysUser user) {
        String password = user.getPassword();
        SysUser queryById = userService.queryById(user.getId());
        if (!queryById.getUserName().equals(user.getUserName())) {
            if (userService.selectByUserName(user.getUserName()) != null) {
                return ResultTool.fail(ResultCode.USER_NAME_ALREADY_EXIST);
            } else {
                queryById.setUserName(user.getUserName());
            }
        }
        if (new BCryptPasswordEncoder().matches(password, queryById.getPassword())) {
            user.setUpdateTime(System.currentTimeMillis());
            user.setPassword(queryById.getPassword());
            String jsonString = JSONObject.toJSONString(userService.update(user));
            SysUser parseObject = JSONObject.parseObject(jsonString, SysUser.class);
            parseObject.setPassword(password);
            return ResultTool.success(parseObject);
        } else {
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        }
    }

}
