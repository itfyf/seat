package com.fyf.example.demo.service.impl;



import org.springframework.stereotype.Service;

import com.fyf.example.demo.service.IRedisService;


/**
 * 存入redis中的key
 *
 * @return
 */
@Service
public class RedisServiceImpl extends IRedisService {
    public static final String REDIS_WINDOW = "WINDOW";
    
    public static final String REDIS_AISLE = "AISLE";
    
    public static final String REDIS_GATE = "GATE";
    
    public static final String REDIS_RANDOM = "RANDOM";
    
    public static final String REDIS_BADY = "BADY";
    
    
}