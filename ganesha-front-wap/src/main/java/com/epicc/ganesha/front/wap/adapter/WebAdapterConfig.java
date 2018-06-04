package com.epicc.ganesha.front.wap.adapter;

import com.epicc.ganesha.front.wap.exception.ExceptionResolver;
import com.epicc.ganesha.front.wap.interceptor.AccessInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Description: 通用适配器
 * Author: lishangmin
 * Created: 2018-05-31 17:17
 */
@Slf4j
@Configuration
public class WebAdapterConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ExceptionResolver exceptionResolver;
    
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(exceptionResolver);
    }

    @Autowired
    AccessInterceptor accessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
    }
}
