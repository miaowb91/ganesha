package com.epicc.ganesha.front.wap.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: kaptcha验证码配置
 * Author: lishangmin
 * Created: 2018-05-23 15:15
 */
@Configuration
@EnableConfigurationProperties(KaptchaProperties.class)
public class KaptchaConfig {

    /**
     * 配置验证码Kaptcha
     */
    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(KaptchaProperties kaptchaProperties){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Config config = new Config(kaptchaProperties.getProperties());
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
