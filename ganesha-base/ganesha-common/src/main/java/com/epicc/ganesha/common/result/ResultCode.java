package com.epicc.ganesha.common.result;

import lombok.Getter;

/**
 * Description: 通用状态代码
 * Author: lishangmin
 * Created: 2018-05-21 15:53
 */
@Getter
public class ResultCode {

    public static ResultCode SUCCESS = new ResultCode("000000","成功");

    public static ResultCode ERROR   = new ResultCode("100000","失败");

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态信息
     */
    private String msg;

    public ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
