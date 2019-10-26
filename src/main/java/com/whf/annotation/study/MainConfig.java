package com.whf.annotation.study;

import com.whf.annotation.study.listener.ListenerContainer;
import com.whf.annotation.study.listener.RedPacketListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;

@Configuration
@ComponentScan(value = "com.whf")
@PropertySource("classpath:Redis.properties")
public class MainConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private String redisPort;

    @Bean("jedisPool")
    public JedisPool getRedisPool() {
        return new JedisPool(redisHost, Integer.parseInt(redisPort));
    }

    @Bean("redPacketListener")
    public RedPacketListener redPacketListener() {
        return new RedPacketListener();
    }

    @Bean
    public ListenerContainer listenerContainer(JedisPool jedisPool) {
        ListenerContainer listenerContainer = new ListenerContainer(jedisPool);
        listenerContainer.add(redPacketListener());
        return listenerContainer;
    }


}
