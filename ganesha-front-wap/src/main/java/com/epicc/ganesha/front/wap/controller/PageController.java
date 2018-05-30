package com.epicc.ganesha.front.wap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 演示页面控制层
 * Author: lishangmin
 * Created: 2018-05-28 14:20
 */
@Controller
@Slf4j
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public String index(){
        return "captcha";
    }

}
