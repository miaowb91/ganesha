package com.epicc.ganesha.front.wap.exception;

import com.alibaba.fastjson.JSON;
import com.epicc.ganesha.common.result.Result;
import com.epicc.ganesha.front.wap.util.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * Description:
 * Author: lishangmin
 * Created: 2018-06-04 18:04
 */
@Component
@Slf4j
public class ExceptionResolver implements HandlerExceptionResolver {

    @Autowired
    Tools tools;

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        Result result;
        //处理公用异常
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (e instanceof ApiException) {//业务失败的异常，如“账号或密码错误”
                ApiException exception = (ApiException) e;
                result = Result.createByError(exception.getCode(),exception.getMessage());
                log.error(
                        handlerMethod.getBean().getClass().getSimpleName()+":"+
                                handlerMethod.getMethod().getName()+":"+
                                exception.getCode() + ":"+
                                exception.getMessage()
                );
            }
            else if (e instanceof ConstraintViolationException){
                ConstraintViolationException exception = (ConstraintViolationException)e;
                result = Result.createByError(exception.getConstraintViolations().iterator().next().getMessage());
                log.error(
                        handlerMethod.getBean().getClass().getSimpleName()+":"+
                                handlerMethod.getMethod().getName()+":"+
                                exception.getConstraintViolations().iterator().next().getMessage()
                );
            }
            else {
                String message = String.format(
                        "接口 [%s] 出现异常，方法：%s.%s，异常：%s 异常摘要：%s",
                        httpServletRequest.getRequestURI(),
                        handlerMethod.getBean().getClass().getSimpleName(),
                        handlerMethod.getMethod().getName(),
                        e.getClass().getSimpleName(),
                        e.getMessage()
                );
                log.error(message);
                result = Result.createByError();
            }
        }
        else{
            result = Result.createByError();
        }
        //返回处理结果
        try {
            tools.render(httpServletResponse,JSON.toJSONString(result));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ModelAndView();
    }

}
