package com.epicc.ganesha.common.vo;

import lombok.Getter;

/**
 * Description: 通用状态代码
 * Author: lishangmin
 * Created: 2018-05-21 15:53
 */
@Getter
public enum  ResultCode {

    SUCCESS("000000","成功"),
    ERROR("100000","失败"),
    MOBILE_FORMAT_ERROR("200001","手机号码格式错误");

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态信息
     */
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
