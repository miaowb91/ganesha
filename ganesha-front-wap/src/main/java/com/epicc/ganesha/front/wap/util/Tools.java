package com.epicc.ganesha.front.wap.util;

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
    private final JedisUtil jedisUtil;

    @Autowired
    public Tools(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    /**
     * 是否超限
     * @param key   关键字
     * @param limit 限制总数
     */
    public boolean increase(String key,Integer limit){
        if(jedisUtil.exists(key)){
            Integer visitCount = Integer.valueOf(jedisUtil.get(key));
            if(visitCount>limit){
                return false;
            }
        }else {
            jedisUtil.set(key,"1");
        }
        return true;
    }
}
