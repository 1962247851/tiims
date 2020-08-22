package com.example.tiims.common.utils;


import com.example.tiims.common.entity.JsonResult;
import com.example.tiims.common.enums.ResultCode;

public class ResultTool {
    public static JsonResult<?> success() {
        return new JsonResult<>(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(true, data);
    }

    public static JsonResult<?> fail() {
        return new JsonResult<>(false);
    }

    public static JsonResult<?> fail(ResultCode resultEnum) {
        return new JsonResult<>(false, resultEnum);
    }
}
