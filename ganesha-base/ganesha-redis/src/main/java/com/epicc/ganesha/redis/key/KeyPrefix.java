package com.epicc.ganesha.redis.key;

/**
 * Description: RedisKey 生成模板接口
 * Author: lishangmin
 * Created: 2018-05-28 09:49
 */
public interface KeyPrefix {

    /**
     * 过期时间
     */
    int expireSeconds();

    /**
     * 获取KEY前缀
     */
    String getPrefix();
}
