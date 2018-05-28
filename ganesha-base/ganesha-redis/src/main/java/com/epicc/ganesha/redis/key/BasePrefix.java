package com.epicc.ganesha.redis.key;

/**
 * Description: RedisKey抽象类
 * Author: lishangmin
 * Created: 2018-05-28 10:26
 */
public abstract class BasePrefix implements KeyPrefix{

    /**
     * 过期时间
     */
    private int expiredSeconds;

    /**
     * 前缀
     */
    private String prefix;

    public BasePrefix(String prefix){
        this.prefix = prefix;
    }

    public BasePrefix(int expiredSeconds,String prefix){
        this.prefix = prefix;
        this.expiredSeconds = expiredSeconds;
    }

    /**
     * 过期时间
     */
    @Override
    public int expireSeconds() {
        return expiredSeconds;
    }

    /**
     * 获取KEY前缀
     */
    @Override
    public String getPrefix() {
        return "ganesha:"+getClass().getSimpleName().toLowerCase() + ":" + prefix + ":";
    }

}
