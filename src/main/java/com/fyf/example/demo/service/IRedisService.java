package com.fyf.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/1 14:57.
 */
public abstract class IRedisService<T> {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
    @Resource
    protected HashOperations<String, String, T> hashOperations;



    /**
     * 添加
     *
     * @param key    key
     * @param doamin 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public void put(String redisKey,String key, T doamin, long expire) {
        hashOperations.put(redisKey, key, doamin);
        if (expire != -1) {
            redisTemplate.expire(redisKey, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     *
     * @param redis key 传入key的名称
     */
    public void remove(String redisKey,String key) {
        hashOperations.delete(redisKey, key);
    }

    /**
     * 查询
     *
     * @param key 查询的key
     * @return
     */
    public T get(String redisKey,String key) {
        return hashOperations.get(redisKey, key);
    }

    /**
     * 获取当前redis库下所有对象
     *
     * @return
     */
    public List<T> getAll(String redisKey) {
        return hashOperations.values(redisKey);
    }

    /**
     * 查询查询当前redis库下所有key
     *
     * @return
     */
    public Set<String> getKeys(String redisKey) {
        return hashOperations.keys(redisKey);
    }

    /**
     * 判断key是否存在redis中
     *
     * @param redis key 传入key的名称
     * @return
     */
    public boolean isKeyExists(String redisKey,String key) {
        return hashOperations.hasKey(redisKey, key);
    }

    /**
     * 查询当前key下缓存数量
     *
     * @return
     */
    public long count(String redisKey) {
        return hashOperations.size(redisKey);
    }

    /**
     * 清空redis
     */
    public void empty(String redisKey) {
        Set<String> set = hashOperations.keys(redisKey);
        set.stream().forEach(key -> hashOperations.delete(redisKey, key));
    }
}
