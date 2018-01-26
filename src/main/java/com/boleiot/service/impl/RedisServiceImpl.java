package com.boleiot.service.impl;

import com.boleiot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    public Object get(String key) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}
