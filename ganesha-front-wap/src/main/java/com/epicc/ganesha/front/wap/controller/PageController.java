package com.epicc.ganesha.front.wap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 演示页面控制层
 * Author: lishangmin
 * Created: 2018-05-28 14:20
 */
@RestController
@Slf4j
public class PageController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
