package com.whf.annotation.study.listener;

import redis.clients.jedis.JedisPubSub;

public abstract  class Listener extends JedisPubSub {
    abstract String topic();
}
