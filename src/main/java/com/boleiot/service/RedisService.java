package com.boleiot.service;

public interface RedisService {
    void set(String key, Object value);

    Object get(String key);

    void set(String key, Object value, long timeout);
}
