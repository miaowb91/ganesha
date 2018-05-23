package com.epicc.ganesha.asset.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description: 资产管理对外开放API服务
 * Author: lishangmin
 * Created: 2018-05-22 14:20
 */
@SpringBootApplication
@EnableEurekaClient
public class AssetApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetApiApplication.class,args);
    }
}
