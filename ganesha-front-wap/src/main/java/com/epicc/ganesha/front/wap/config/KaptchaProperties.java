package com.epicc.ganesha.front.wap.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.epicc.ganesha.front.wap.config.KaptchaProperties.KAPTCHA_PREFIX;

/**
 * Description: Kaptcha验证码属性
 * Author: lishangmin
 * Created: 2018-05-31 15:44
 */
@Component
@ConfigurationProperties(prefix = KAPTCHA_PREFIX)
public class KaptchaProperties {

    public static final String KAPTCHA_PREFIX="kaptcha";

    private Properties properties= new Properties();

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
