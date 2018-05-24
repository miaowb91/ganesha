package com.epicc.ganesha.front.wap.constant;

/**
 * Description: 各种配置常量
 * Author: lishangmin
 * Created: 2018-05-23 15:15
 */
public interface Constant {

    //登录令牌token key前缀
    String ACCESS_TOKEN_PRE = "ganesha:account:token:";

    //用户ID 关联 登录令牌 token key前缀
    String USER_ID_PRE = "ganesha:account:id:";

    //设置TOKEN过期时间 7天
    Integer EXPIRED_TIME = 60 * 60 * 24 * 7;

    //验证码 key前缀
    String CAPTCHA_KEY_PRE = "ganesha:account:captcha:";

    //验证码过期时间 5分钟
    Integer CAPTCHA_EXPIRED_TIME = 60 * 5;

    //注册短信验证码
    String SMS_VERIFY_KEY_PRE = "ganesha:account:sms:";

    //注册短信验证码 5分钟
    Integer SMS_VERIFY_EXPIRED_TIME = 60 * 5;

    //手机 发送短信计数器 key前缀
    String MOBILE_COUNTER_KEY_PRE = "ganesha:mobile:counter:";

    //手机 发送短信计数器 24小时 只允许 5次
    Integer MOBILE_COUNTER_EXPIRED_TIME = 60 * 60 * 24;

    //手机 访问接口数量统计 key前缀
    String MOBILE_VISIT_KEY_PRE = "ganesha:mobile:visit:";

    //手机 访问接口数量统计 24小时
    Integer MOBILE_VISIT_EXPIRED_TIME = 60 * 60 * 24;

    //手机 发送间隔计时 key前缀
    String MOBILE_SEND_KEY_PRE = "ganesha:mobule:send:";

    //手机 发送间隔计时 1分钟 只允许一次
    Integer MOBILE_SEND_EXPIRED_TIME = 60;


}
