package com.epicc.ganesha.front.wap.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description: 限流注解
 * Author: lishangmin
 * Created: 2018-06-04 16:45
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

    //限流时间
    int seconds();

    //限流次数
    int limit();

    //限流关键字
    String key();

}
