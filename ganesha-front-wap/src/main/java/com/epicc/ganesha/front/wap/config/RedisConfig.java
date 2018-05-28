package com.epicc.ganesha.front.wap.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "redis")
@Setter
@Getter
public class RedisConfig {

    private boolean sentinel;
    private String masterName;
    private int maxIdle;
    private int maxTotal;
    private int maxWaitMillis;
    private String hosts;
    private String password;
    private int db;

    @Bean
    public Pool<Jedis> jedisPool() {
        if(StringUtils.isBlank(password)){
            password = null;
        }
        if(sentinel) {
            //哨兵集群配置
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            Set<String> sentinels = new HashSet<>();
            String[] hostConfigs = hosts.split(",");
            for (String hostConfig1 : hostConfigs) {
                String[] hostConfig = hostConfig1.split(":");
                sentinels.add(
                        (new HostAndPort(hostConfig[0], Integer.parseInt(hostConfig[1]))).toString()
                );
            }
            return new JedisSentinelPool(masterName, sentinels, jedisPoolConfig,
                    Protocol.DEFAULT_TIMEOUT, password, db);
        } else {
            //单点配置
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            String[] hostConfig = hosts.split(":");
            return new JedisPool(jedisPoolConfig, hostConfig[0], Integer.parseInt(hostConfig[1]),
                    Protocol.DEFAULT_TIMEOUT, password, db);
        }
    }

}
