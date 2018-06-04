package com.epicc.ganesha.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
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

    public static <T> Result<T> createByError(ResultCode resultCode){
        return new Result<>(resultCode.getCode(),resultCode.getMsg());
    }

    public static <T> Result<T> createByError(ResultCode resultCode,T data){
        return new Result<>(resultCode.getCode(),resultCode.getMsg(),data);
    }

    //JackSon Ignore For RestController
    @JsonIgnore
    //FastJson Ignore For Normal Used
    @JSONField(serialize = false)
    public boolean isSuccess(){
        return code.equals(ResultCode.SUCCESS.getCode());
    }
}
