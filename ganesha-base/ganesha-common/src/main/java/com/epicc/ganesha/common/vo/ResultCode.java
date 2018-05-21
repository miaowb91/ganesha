package com.epicc.ganesha.common.vo;

import lombok.Getter;

/**
 * Description: 通用状态代码
 * Author: lishangmin
 * Created: 2018-05-21 15:53
 */
@Getter
public enum  ResultCode {

    Success("0000","成功"),
    Error("1000","失败");

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
