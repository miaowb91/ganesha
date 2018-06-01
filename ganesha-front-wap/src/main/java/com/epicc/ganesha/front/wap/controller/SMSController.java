package com.epicc.ganesha.front.wap.controller;

import com.epicc.ganesha.common.result.Result;
import com.epicc.ganesha.common.result.ResultCode;
import com.epicc.ganesha.common.util.CommonUtil;
import com.epicc.ganesha.front.wap.config.APIErrorCode;
import com.epicc.ganesha.front.wap.exception.ApiException;
import com.epicc.ganesha.front.wap.redis.CaptchaKey;
import com.epicc.ganesha.front.wap.redis.MobileSendKey;
import com.epicc.ganesha.front.wap.service.SMSService;
import com.epicc.ganesha.front.wap.util.Tools;
import com.epicc.ganesha.redis.service.RedisService;
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
    @Autowired
    private Producer captchaProducer;

    //Redis简单客户端
    @Autowired
    private RedisService redisService;

    //简单工具类
    @Autowired
    private Tools tools;

    //短信服务
    @Autowired
    private SMSService smsService;

    //IP请求限制
    private static Integer IP_LIMIT_COUNT = 500;

    //手机请求限制
    private static Integer MOBILE_LIMIT_COUNT = 5;

    //手机只允许访问30次接口
    private static Integer MOBILE_VISIT_COUNT = 30;

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

        //4. 将验证码放入Redis
        redisService.set(CaptchaKey.captchaMobile, mobile,capText); //保存到redis

        //5. 回写流
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
            log.info("手机号验证失败:{}",mobile);
            return Result.createByError(ResultCode.MOBILE_FORMAT_ERROR);
        }

        //2. 手机号访问计数
        if (!tools.increase(MobileSendKey.visit,mobile,MOBILE_VISIT_COUNT)){
            log.info("{} 超过访问次数:{}",mobile,MOBILE_VISIT_COUNT);
            return Result.createByError(APIErrorCode.OVER_VISIT_ERROR);
        }else{
            redisService.incr(MobileSendKey.visit,mobile);
        }

        //3. 校验验证码
        String captchaStore = redisService.get(CaptchaKey.captchaMobile,mobile,String.class);
        redisService.delete(CaptchaKey.captchaMobile,mobile);
        if(captchaStore == null || !captcha.equals(captchaStore)){
            return Result.createByError(APIErrorCode.CAPTCHA_ERROR);
        }

        //4. 手机号发送计数
        if (!tools.increase(MobileSendKey.counter,mobile,MOBILE_LIMIT_COUNT)){
            log.info("{} 超过发送次数:{}",mobile,MOBILE_LIMIT_COUNT);
            return Result.createByError(APIErrorCode.OVER_SEND_ERROR);
        }

        //5. 判断发送间隔
        if (redisService.exists(MobileSendKey.gap,mobile)) {
            log.info("{} 发送频率过高",mobile);
            return Result.createByError(APIErrorCode.AFTER_TRY_ERROR);
        }

        //6. 发送短信
        Result sendResult = smsService.send(mobile,"");

        //7. 设置发送间隔,发送成功计数
        if (sendResult.isSuccess()) {
            redisService.set(MobileSendKey.gap, mobile,mobile);
            redisService.incr(MobileSendKey.counter,mobile);
        }else{
            return Result.createByError();
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
            throw new ApiException(ResultCode.MOBILE_FORMAT_ERROR);
        }
        return Result.createBySuccess();
    }
}
