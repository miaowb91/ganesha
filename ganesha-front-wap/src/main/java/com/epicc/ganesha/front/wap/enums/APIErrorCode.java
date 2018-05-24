package com.epicc.ganesha.front.wap.enums;

import lombok.Getter;

/**
 * Description: 业务模块异常
 * Author: lishangmin
 * Created: 2018-05-23 17:14
 */
@Getter
public enum APIErrorCode {

    OVER_SEND_ERROR("300001","超过发送次数"),
    FROZEN_ERROR("300002","手机号已被冻结"),
    AFTER_TRY_ERROR("300003","请稍后重试"),
    OVER_VISIT_ERROR("300004","超过访问次数"),
    CAPTCHA_ERROR("300005","验证码错误")
    ;

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态信息
     */
    private String msg;

    APIErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
