package com.epicc.ganesha.front.wap;

import com.epicc.ganesha.redis.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Description: 验证短信防刷机制演示Demo启动程序入口
 * Author: lishangmin
 * Created: 2018-05-23 15:15
 */
@SpringBootApplication
@Import({RedisConfig.class})
@ComponentScan(basePackages = {"com.epicc.ganesha"})
public class FrontWapApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontWapApplication.class,args);
    }

}
