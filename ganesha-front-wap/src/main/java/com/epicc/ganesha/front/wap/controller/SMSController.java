package com.epicc.ganesha.front.wap.controller;

import com.epicc.ganesha.common.util.CommonUtil;
import com.epicc.ganesha.common.vo.Result;
import com.epicc.ganesha.common.vo.ResultCode;
import com.epicc.ganesha.front.wap.constant.Constant;
import com.epicc.ganesha.front.wap.util.JedisUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Description: 验证短信防刷机制接口
 * Author: lishangmin
 * Created: 2018-05-23 15:18
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/sms")
public class SMSController {

    //验证码生成器
    private final Producer captchaProducer;

    //Redis简单客户端
    private final JedisUtil jedisUtil;

    @Autowired
    public SMSController(Producer captchaProducer, JedisUtil jedisUtil) {
        this.captchaProducer = captchaProducer;
        this.jedisUtil = jedisUtil;
    }

    /**
     * 获取验证码
     * @param mobile 手机号
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] captcha(
            @RequestParam("mobile") String mobile
    ){
        log.info("mobile:{}",mobile);
        //1. 验证手机号
        if(!CommonUtil.isMobile(mobile)){
            return null;
        }

        //2. 生成验证码
        String capText = captchaProducer.createText();
        log.info("capText:{} ",capText);

        //3. 生成图片流
        BufferedImage bi = captchaProducer.createImage(capText);

        //4. 将验证码放入
        String key = Constant.CAPTCHA_KEY_PRE + mobile; //验证码 redis key
        jedisUtil.set(key, capText,Constant.CAPTCHA_EXPIRED_TIME); //保存到redis

        //4. 回写流
        try(ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "png", bao);//生成流
            return bao.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 发送短信（不包含业务接口）
     * @param mobile   手机号
     * @param captcha  验证码
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Result send(
            @RequestParam("mobile") String mobile,
            @RequestParam("captcha")String captcha
    ){
        log.info("mobile:{},captcha:{}",mobile,captcha);
        //1. 验证手机号
        if(!CommonUtil.isMobile(mobile)){
            return Result.createByError(
                    ResultCode.MOBILE_FORMAT_ERROR.getCode(),
                    ResultCode.MOBILE_FORMAT_ERROR.getMsg()
            );
        }

        return Result.createBySuccess();
    }

    /**
     * 发送短信（包含业务规范)
     * @param mobile   手机号
     * @param captcha  验证码
     * @param t        验证类型 1:登录 2:注册
     */
    public Result fly(
            @RequestParam("mobile") String mobile,
            @RequestParam("captcha")String captcha,
            @RequestParam("t") String t
    ){
        log.info("mobile:{},captcha:{},type:{}",mobile,captcha,t);
        //1. 验证手机号
        if(!CommonUtil.isMobile(mobile)){
            return Result.createByError(
                    ResultCode.MOBILE_FORMAT_ERROR.getCode(),
                    ResultCode.MOBILE_FORMAT_ERROR.getMsg()
            );
        }


        return Result.createBySuccess();
    }
}
