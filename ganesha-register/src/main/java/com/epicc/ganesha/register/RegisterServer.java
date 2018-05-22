package com.epicc.ganesha.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Description: 注册中心服务启动主程序
 * Author: lishangmin
 * Created: 2018-05-21 14:39
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterServer {

    public static void main (String[] args) {
        SpringApplication.run(RegisterServer.class,args);
    }

}
