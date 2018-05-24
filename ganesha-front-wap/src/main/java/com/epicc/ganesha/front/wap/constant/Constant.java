package com.epicc.ganesha.front.wap.constant;

/**
 * Description: 各种配置常量
 * Author: lishangmin
 * Created: 2018-05-23 15:15
 */
public interface Constant {

    //登录令牌token key前缀
    String ACCESS_TOKEN_PRE = "ganesha:account:client:token:";

    //用户ID 关联 登录令牌 token key前缀
    String USER_ID_PRE = "ganesha:account:client:id:";

    //设置TOKEN过期时间 7天
    Integer EXPIRED_TIME = 60 * 60 * 24 * 7;

    //验证码 key前缀
    String CAPTCHA_KEY_PRE = "ganesha:account:client:captcha:";

    //验证码过期时间 5分钟
    Integer CAPTCHA_EXPIRED_TIME = 60 * 5;

    //注册短信验证码
    String SMS_VERIFY_KEY_PRE = "ganesha:account:client:sms:";

    //注册短信验证码 5分钟
    Integer SMS_VERIFY_EXPIRED_TIME = 60 * 5;

}
