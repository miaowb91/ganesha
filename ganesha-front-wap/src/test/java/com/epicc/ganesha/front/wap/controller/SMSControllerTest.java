package com.epicc.ganesha.front.wap.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Description: 控制层单元测试
 * Author: lishangmin
 * Created: 2018-06-01 10:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SMSControllerTest {

    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new SMSController()).build();
    }

    @Test
    public void captcha() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/captcha"));
    }

    @Test
    public void send() throws Exception {
    }

    @Test
    public void fly() throws Exception {
    }

}