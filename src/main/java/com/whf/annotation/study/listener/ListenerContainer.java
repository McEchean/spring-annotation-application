package com.whf.annotation.study.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ListenerContainer {
    private List<Listener> listeners = new ArrayList<>();
    private JedisPool jedisPool;
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(20,30,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5000), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

    public ListenerContainer(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void add(Listener listener) {
        this.listeners.add(listener);
        executor.submit(() -> startListen(listener));
    }

    public void remove(Listener listener) {
        if(!CollectionUtils.isEmpty(listeners)) {
            listeners.remove(listener);
        }
    }

    private void startListen(Listener listener) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.psubscribe(listener,listener.topic());
        }catch (Exception e) {
            log.error("subscribe some topic error!");
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        this.executor.shutdown();
    }



}
