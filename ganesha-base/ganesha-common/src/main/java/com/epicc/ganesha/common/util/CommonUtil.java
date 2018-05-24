package com.epicc.ganesha.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
     * 校验是否为空
     */
    public static Integer checkNull(Integer value){
        if(value == null){
            return 0;
        }
        return value;
    }

    /**
     * 校验是否为空
     */
    public static Double checkNull(Double value){
        if(value == null){
            return 0D;
        }
        return value;
    }

    /**
     * 校验是否为空
     */
    public static Long checkNull(Long value){
        if(value == null){
            return 0L;
        }
        return value;
    }

    /**
     * 校验是否为空
     */
    public static String checkNull(String value){
        if(value == null){
            return "";
        }
        return value;
    }

    /**
     * 校验是否为空
     */
    public static List<String> checkNull(List<String> value){
        if(value == null){
            return new ArrayList<>();
        }
        return value;
    }

    /**
     * 校验是否为空
     */
    public static BigDecimal checkNull(BigDecimal value) {
        if(value == null){
            return new BigDecimal(0.00);
        }
        return value;
    }

    /**
     * 生成UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
