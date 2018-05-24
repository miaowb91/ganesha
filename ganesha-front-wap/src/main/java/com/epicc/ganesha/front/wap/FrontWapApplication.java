package com.epicc.ganesha.front.wap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 验证短信防刷机制演示Demo启动程序入口
 * Author: lishangmin
 * Created: 2018-05-23 15:15
 */
@SpringBootApplication
//@Import({CaptchaConfig.class, RedisConfig.class})
public class FrontWapApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontWapApplication.class,args);
    }

}
