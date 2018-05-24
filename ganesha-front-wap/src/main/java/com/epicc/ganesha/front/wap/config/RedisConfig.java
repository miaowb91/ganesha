package com.epicc.ganesha.front.wap.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;
import redis.clients.util.Pool;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: Redis配置
 * Author: lishangmin
 * Created: 2018-05-23 15:18
 */
@Configuration
public class RedisConfig {

    @Value("${redis.sentinel}")
    private boolean redis_sentinel;
    @Value("${redis.masterName}")
    private String redis_masterName;
    @Value("${redis.maxIdle}")
    private int redis_maxIdle;
    @Value("${redis.maxTotal}")
    private int redis_maxTotal;
    @Value("${redis.maxWaitMillis}")
    private int redis_maxWaitMillis;
    @Value("${redis.hosts}")
    private String redis_hosts;
    @Value("${redis.password}")
    private String redis_password;
    @Value("${redis.db}")
    private int redis_db;

    @Bean
    public Pool<Jedis> jedisPool() {
        if(StringUtils.isBlank(redis_password)){
            redis_password = null;
        }
        if(redis_sentinel) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(redis_maxIdle);
            jedisPoolConfig.setMaxTotal(redis_maxTotal);
            jedisPoolConfig.setMaxWaitMillis(redis_maxWaitMillis);
            Set<String> sentinels = new HashSet<>();
            String[] hostConfigs = redis_hosts.split(",");
            for (String hostConfig1 : hostConfigs) {
                String[] hostConfig = hostConfig1.split(":");
                sentinels.add(
                        (new HostAndPort(hostConfig[0], Integer.parseInt(hostConfig[1]))).toString()
                );
            }
            return new JedisSentinelPool(redis_masterName, sentinels, jedisPoolConfig,
                    Protocol.DEFAULT_TIMEOUT, redis_password, redis_db);
        } else {
            //初始化jedis
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(redis_maxIdle);
            jedisPoolConfig.setMaxTotal(redis_maxTotal);
            jedisPoolConfig.setMaxWaitMillis(redis_maxWaitMillis);
            String[] hostConfig = redis_hosts.split(":");
            return new JedisPool(jedisPoolConfig, hostConfig[0], Integer.parseInt(hostConfig[1]),
                    Protocol.DEFAULT_TIMEOUT, redis_password, redis_db);
        }
    }

}
