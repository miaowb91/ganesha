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



}
