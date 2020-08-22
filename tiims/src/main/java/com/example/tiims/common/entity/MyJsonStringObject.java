package com.example.tiims.common.entity;

import com.alibaba.fastjson.JSON;

/**
 * 默认通过JSON实现toString方法
 *
 * @author qq1962247851
 * @date 2020/6/16 18:38
 */
public class MyJsonStringObject {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
