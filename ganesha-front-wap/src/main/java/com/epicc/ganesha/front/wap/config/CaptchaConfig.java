package com.epicc.ganesha.front.wap.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Description: kaptcha验证码配置
 * Author: lishangmin
 * Created: 2018-05-23 15:15
 */
@Configuration
public class CaptchaConfig {

    /**
     * 配置验证码Kaptcha
     */
    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER, "no");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "30,144,255");
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "125");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "45");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "1");
        //properties.setProperty("Constants.KAPTCHA_OBSCURIFICATOR_IMPL", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "white");
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, "30,144,255");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
