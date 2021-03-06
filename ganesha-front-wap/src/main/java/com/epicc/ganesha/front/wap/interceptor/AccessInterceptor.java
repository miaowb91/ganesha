package com.epicc.ganesha.front.wap.interceptor;

import com.alibaba.fastjson.JSON;
import com.epicc.ganesha.common.result.Result;
import com.epicc.ganesha.front.wap.config.APIErrorCode;
import com.epicc.ganesha.front.wap.redis.MobileSendKey;
import com.epicc.ganesha.front.wap.util.Tools;
import com.epicc.ganesha.redis.key.KeyPrefix;
import com.epicc.ganesha.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 限流注解实现
 * Author: lishangmin
 * Created: 2018-06-04 16:56
 */
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisService redisService;

    @Autowired
    Tools tools;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int limit = accessLimit.limit();
            String key = accessLimit.key();
            String keyValue = request.getParameter(key);
            KeyPrefix keyPrefix = MobileSendKey.withExpire(seconds);
            Integer count = redisService.get(keyPrefix,keyValue,Integer.class);
            if(count == null){
                redisService.set(keyPrefix, keyValue,1);
            }else if(count < limit){
                redisService.incr(keyPrefix, keyValue);
            }else{
                String str  = JSON.toJSONString(Result.createByError(APIErrorCode.OVER_VISIT_ERROR));
                tools.render(response, str);
                return false;
            }
        }
        return true;
    }


}
