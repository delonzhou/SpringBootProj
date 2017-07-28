package com.marving.boot.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by mercop on 2017/7/27.
 */


@Service
public class RedisCache {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisTemplate redisTemplate;

    public String getInfo(String key){
        if(key == null)
            return null;
        return stringRedisTemplate.opsForValue().get(key);
    }


    public void setInfo(String key, String value){
        if(key == null)
            return;
        stringRedisTemplate.opsForValue().set(key,value);
    }


}
