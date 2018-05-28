package com.epicc.ganesha.front.wap.config;

import com.epicc.ganesha.common.result.ResultCode;
import lombok.Getter;

/**
 * Description: 业务模块异常
 * Author: lishangmin
 * Created: 2018-05-23 17:14
 */
@Getter
public class APIErrorCode extends ResultCode{

    public static APIErrorCode OVER_SEND_ERROR = new APIErrorCode("300001","超过发送次数");
    public static APIErrorCode FROZEN_ERROR = new APIErrorCode("300002","手机号已被冻结");
    public static APIErrorCode AFTER_TRY_ERROR = new APIErrorCode("300003","请稍后重试");
    public static APIErrorCode OVER_VISIT_ERROR = new APIErrorCode("300004","超过访问次数");
    public static APIErrorCode CAPTCHA_ERROR = new APIErrorCode("300005","验证码错误");

    private APIErrorCode(String code, String msg) {
        super(code,msg);
    }

}
