package com.epicc.ganesha.front.wap.redis;

import com.epicc.ganesha.redis.key.BasePrefix;

/**
 * Description: 验证码KEY
 * Author: lishangmin
 * Created: 2018-05-28 10:57
 */
public class CaptchaKey extends BasePrefix {

    private CaptchaKey(int expiredSeconds, String prefix) {
        super(expiredSeconds, prefix);
    }

    public static CaptchaKey captchaMobile = new CaptchaKey(60 * 5,"mobile");

}
