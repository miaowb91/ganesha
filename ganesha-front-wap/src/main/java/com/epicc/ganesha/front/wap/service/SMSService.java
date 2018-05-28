package com.epicc.ganesha.front.wap.service;

import com.epicc.ganesha.common.result.Result;

/**
 * Description: 发送短信DEMO
 * Author: lishangmin
 * Created: 2018-05-24 17:50
 */
public interface SMSService {

    /**
     * 发送短信
     * @param mobile 手机号
     * @param t      短信涉及业务类型
     */
    Result send(String mobile,String t);

    /**
     * 发送短信
     * @param mobile 手机号
     * @param t      短信涉及业务类型
     * @param text   短信内容
     */
    Result send(String mobile,String t,String text);
}
