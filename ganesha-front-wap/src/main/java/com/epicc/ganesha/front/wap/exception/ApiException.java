package com.epicc.ganesha.front.wap.exception;

import com.epicc.ganesha.common.result.ResultCode;

/**
 * Description: 通用异常
 * Author: lishangmin
 * Created: 2018-05-31 17:20
 */
public class ApiException extends RuntimeException{

    //错误代码
    private String code;

    public ApiException() {
    }

    public ApiException(String code) {
        this.code = code;
    }

    public ApiException(String code,String message) {
        super(message);
        this.code = code;
    }

    public ApiException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApiException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
