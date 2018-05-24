package com.epicc.ganesha.front.wap.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;


@Slf4j
@Component
public class JedisUtil {

    private final Pool<Jedis> jedisPool;

    @Autowired
    public JedisUtil(Pool<Jedis> jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * redis 连接池获取 链接客户端
     */
    private Jedis getResource(){
        try{
            log.debug("NumActive:{}",jedisPool.getNumActive());
            log.debug("NumIdle:{}",jedisPool.getNumIdle());
            log.debug("NumWaiters:{}",jedisPool.getNumWaiters());
            return jedisPool.getResource();
        }catch (Exception e){
            log.error("getResource error",e.toString());
            throw new RuntimeException("Jedis getResource Error");
        }
    }

    /**
     * 获取KEY的对应的值
     * @param key 关键字
     */
    public String get(String key){
        try (Jedis jedis = this.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            log.error("jedis get Key:{}", key, e);
            throw new RuntimeException("Jedis get Error");
        }
    }

    /**
     * 判断Key是否存在
     * @param key 关键字
     */
    public boolean exists(String key){
        try (Jedis jedis = this.getResource()) {
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("jedis exists Key:{}", key, e);
            return false;
        }
    }

    /**
     * 给对应key赋值
     * @param key   关键字
     * @param value 值
     */
    public String set(String key, String value){
        try (Jedis jedis = this.getResource()) {
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("jedis set Key:{}", key, e);
            throw new RuntimeException("Jedis set Error");
        }
    }

    /**
     * 设置过期时间
     * @param key         关键字
     * @param expiredTime 过期时间，单位：秒
     */
    public Long expired(String key, Integer expiredTime){
        try (Jedis jedis = this.getResource()) {
            return jedis.expire(key, expiredTime);
        } catch (Exception e) {
            log.error("jedis expire Key:{}", key, e);
            throw new RuntimeException("Jedis expire Error");
        }
    }

    /**
     * 删除key
     * @param key 关键字
     */
    public Long del(String key){
        try (Jedis jedis = this.getResource()) {
            return jedis.del(key);
        } catch (Exception e) {
            log.error("jedis del Key:{}", key, e);
            throw new RuntimeException("Jedis del Error");
        }
    }

    /**
     * 给对应Key赋值并设置过期时间
     * @param key         关键字
     * @param value       值
     * @param expiredTime 过期时间，单位秒
     */
    public void set(String key,String value,Integer expiredTime){
        try(Jedis jedis = this.getResource()){
            jedis.set(key, value);
            jedis.expire(key,expiredTime);
        }catch (Exception e){
            log.error("jedis set Key:{}", key, e);
            throw new RuntimeException("Jedis set Error");
        }
    }

    /**
     * 计数器
     * @param key 关键字
     */
    public void incr(String key){
        try(Jedis jedis = this.getResource()){
            jedis.incr(key);
        }catch (Exception e){
            log.error("jedis incr Key:{}", key, e);
            throw new RuntimeException("Jedis incr Error");
        }
    }
}
