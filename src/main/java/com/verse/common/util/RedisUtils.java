package com.verse.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setExpire(String key, String value, Long second) {
        redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void setNX(String key, String value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void setNXExpire(String key, String value, Long second) {
        redisTemplate.opsForValue().setIfAbsent(key, value, second, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void expire(String key, Long second) {
        redisTemplate.expire(key, second, TimeUnit.SECONDS);
    }

    public void expireAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    public void setHash(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public String getHash(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }


}
