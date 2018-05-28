package com.epicc.ganesha.front.wap.redis;

import com.epicc.ganesha.redis.key.BasePrefix;

/**
 * Description: 短信发送Key前缀
 * Author: lishangmin
 * Created: 2018-05-28 11:20
 */
public class MobileSendKey extends BasePrefix{

    public MobileSendKey(int expiredSeconds, String prefix) {
        super(expiredSeconds, prefix);
    }

    public static MobileSendKey visit = new MobileSendKey(60 * 5,"visit");

    public static MobileSendKey counter = new MobileSendKey(60 * 60 * 24,"counter");

    public static MobileSendKey gap = new MobileSendKey(60,"gap");
}
