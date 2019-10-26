package com.whf.annotation.study.service;

import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public interface RedisService {
    void setList(String key, List<String> list) throws Exception;

    String get(String key) throws Exception;

    void set(String key, String value) throws  Exception;

    Long setNx(String key, String value) throws Exception;

    String listPop(String key);

    void expireKey(String key, int second);

    public Double getZSetScore(String key,String value);

    public void addZSet(String key,String value, Double score);

    public Set<Tuple> getAllZSet(String key);
}
