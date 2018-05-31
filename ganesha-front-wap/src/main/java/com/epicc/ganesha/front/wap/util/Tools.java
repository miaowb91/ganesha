package com.epicc.ganesha.front.wap.util;

import com.epicc.ganesha.redis.key.BasePrefix;
import com.epicc.ganesha.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 一些公用代码 减少重复开发
 * Author: lishangmin
 * Created: 2018-05-24 18:15
 */
@Component
public class Tools {

    //redis 工具类
    private final RedisService redisService;

    @Autowired
    public Tools(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 是否超限
     * @param key   关键字
     * @param limit 限制总数
     */
    public boolean increase(BasePrefix prefix,String key, Integer limit){

        // 如果不存在新增一个计数器
        if(redisService.exists(prefix,key)){
            Integer visitCount = redisService.get(prefix,key,Integer.class);
            if(visitCount>limit){
                return false;
            }
        }else {
            redisService.set(prefix,key,1);
        }
        return true;
    }
}
