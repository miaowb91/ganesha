package com.epicc.ganesha.front.wap.validator;

import com.epicc.ganesha.common.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description: 验证手机号注解实现
 * Author: lishangmin
 * Created: 2018-06-04 15:32
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile isMobile) {
        this.required = isMobile.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required) {
            return CommonUtil.isMobile(value);
        }else {
            return StringUtils.isEmpty(value) || CommonUtil.isMobile(value);
        }
    }
}
