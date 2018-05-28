package com.epicc.ganesha.front.wap.service.impl;

import com.epicc.ganesha.common.result.Result;
import com.epicc.ganesha.front.wap.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: 短信服务实现
 * Author: lishangmin
 * Created: 2018-05-24 17:53
 */
@Service
@Slf4j
public class SMSServiceImpl implements SMSService{

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @param t      短信涉及业务类型
     */
    @Override
    public Result send(String mobile, String t) {
        log.info("Send Short Message Mobile:{},Type:{}",mobile,t);
        return Result.createBySuccess();
    }

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @param t      短信涉及业务类型
     * @param text   短信内容
     */
    @Override
    public Result send(String mobile, String t, String text) {
        log.info("Send Short Message Mobile:{},Type:{},Text:{}",mobile,t,text);
        return Result.createBySuccess();
    }
}
