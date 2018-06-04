package com.epicc.ganesha.front.wap.constant;

/**
 * Description: 默认常量配置
 * Author: lishangmin
 * Created: 2018-06-04 15:07
 */
public interface DefaultConstants {

    //IP请求限制
    Integer IP_LIMIT_COUNT = 500;

    //手机请求限制
    Integer MOBILE_LIMIT_COUNT = 5;

    //手机只允许访问30次接口
    Integer MOBILE_VISIT_COUNT = 30;

    //默认字符集编码
    String ENCODING = "UTF-8";

    //默认JSON CONTENT_TYPE
    String JSON_HEADER = "application/json;charset=UTF-8";

}
