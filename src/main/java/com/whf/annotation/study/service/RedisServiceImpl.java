package com.whf.annotation.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisPool jedisPool;

    public void setList(String key, List<String> list) throws Exception {
        if(!CollectionUtils.isEmpty(list)) {
            try(Jedis jedis = jedisPool.getResource()) {
                list.forEach(item -> {
                    jedis.lpush(key,item);
                });
            }
        }

    }

    public String get(String key) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public void set(String key, String value) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.set(key,value);
        }
    }

    @Override
    public Long setNx(String key, String value) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.setnx(key,value);
        }
    }

    public String listPop(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.rpop(key);
        }
    }

    @Override
    public void expireKey(String key, int second) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.expire(key,second);
        }
    }

    @Override
    public Double getZSetScore(String key,String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.zscore(key, value);
        }
    }

    @Override
    public void addZSet(String key,String value, Double score) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.zadd(key,score,value);
        }
    }

    @Override
    public Set<Tuple> getAllZSet(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.zrevrangeWithScores(key, 0, -1);
        }
    }

}
