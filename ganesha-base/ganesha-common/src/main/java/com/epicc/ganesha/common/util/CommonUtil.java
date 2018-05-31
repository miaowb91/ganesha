package com.epicc.ganesha.common.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 简单通用工具类
 * Author: lishangmin
 * Created: 2018-05-21 15:53
 */
public class CommonUtil {

    /**
     * 验证手机号码
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param mobile 手机号
     * @return true 通过 /false 失败
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        return regExMatcher(mobile, regex);
    }

    /**
     * 正则验证
     * @param regEx 正则表达式
     * @param str   被验证字符串
     * @return true 通过 false 失败
     */
    public static boolean regExMatcher(String str, String regEx){
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 生成UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
