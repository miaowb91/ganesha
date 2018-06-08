package com.epicc.ganesha.front.wap.util;

import com.epicc.ganesha.front.wap.constant.DefaultConstants;
import com.epicc.ganesha.redis.key.BasePrefix;
import com.epicc.ganesha.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

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

    /**
     * Response Body
     * @param response HttpServletResponse
     * @param result   返回结果
     */
    public void render(HttpServletResponse response, String result)throws Exception {
        response.setCharacterEncoding(DefaultConstants.ENCODING);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(DefaultConstants.JSON_HEADER);
        OutputStream out = response.getOutputStream();
        out.write(result.getBytes(DefaultConstants.ENCODING));
        out.flush();
        out.close();
    }
}
