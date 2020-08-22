package com.example.tiims.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.utils.*;
import com.example.tiims.redis.RedisCache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author qq1962247851
 */
@Api(description = "验证码API接口")
@RestController
public class CaptchaController {
    private static final Integer CAPTCHA_EXPIRATION = 2;
    private final RedisCache redisCache;

    public CaptchaController(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    /**
     * 生成验证码
     */
    @ApiOperation(value = "生成验证码", notes = "成功后验证码图片以BASE64形式传递过来，需要再进行一次解码\nuuid字段登录要用，有效期2分钟")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败")
    })
    @GetMapping(value = "/captchaImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonResult<?> getCode() throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constant.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey, verifyCode, CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", uuid);
        jsonObject.put("img", Base64.encode(stream.toByteArray()));
        try {
            return ResultTool.success(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultTool.fail();
        } finally {
            stream.close();
        }
    }
}
