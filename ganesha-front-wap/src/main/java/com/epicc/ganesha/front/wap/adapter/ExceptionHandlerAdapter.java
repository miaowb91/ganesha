package com.epicc.ganesha.front.wap.adapter;

import com.alibaba.fastjson.JSON;
import com.epicc.ganesha.common.result.Result;
import com.epicc.ganesha.front.wap.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Description: 通用异常处理适配器
 * Author: lishangmin
 * Created: 2018-05-31 17:17
 */
@Slf4j
@Configuration
public class ExceptionHandlerAdapter extends WebMvcConfigurerAdapter {
    
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
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
                    } else {
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
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
                httpServletResponse.setStatus(HttpStatus.OK.value());
                try {
                    httpServletResponse.getWriter().write(JSON.toJSONString(result));
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
                return new ModelAndView();
            }
        });
    }
}
