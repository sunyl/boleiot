package com.boleiot.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static <T> String toJson(T object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
}
