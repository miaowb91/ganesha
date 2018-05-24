package com.epicc.ganesha.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description: 通用返回模板
 * Author: lishangmin
 * Created: 2018-05-21 15:41
 */
@Getter
@Setter
public class Result<T> implements Serializable {

    /**
     * 代码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 内容
     */
    private T data;

    public Result() {
    }

    public Result(String code,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> createBySuccess(){
        return new Result<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg());
    }

    public static <T> Result<T> createBySuccessWithMsg(String msg){
        return new Result<>(ResultCode.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> createBySuccess(T data){
        return new Result<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),data);
    }

    public static <T> Result<T> createBySuccess(String msg, T data){
        return new Result<>(ResultCode.SUCCESS.getCode(),msg,data);
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> createByError(){
        return new Result<>(ResultCode.ERROR.getCode(),ResultCode.ERROR.getMsg());
    }

    public static <T> Result<T> createByError(String msg){
        return new Result<>(ResultCode.ERROR.getCode(),msg);
    }

    public static <T> Result<T> createByError(String code, String msg){
        return new Result<>(code,msg);
    }

    public boolean isSuccess(){
        if(code.equals(ResultCode.SUCCESS.getCode())){
            return true;
        }
        return false;
    }
}
