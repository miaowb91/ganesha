package com.epicc.ganesha.common.vo;

import com.epicc.ganesha.common.result.Result;
import com.epicc.ganesha.common.result.ResultCode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Description: 通用模板测试
 * Author: lishangmin
 * Created: 2018-05-21 16:42
 */
public class ResultTest {

    @Test
    public void createBySuccess() throws Exception {
        //测试基本返错误码、错误信息是否正确
        Result result = Result.createBySuccess();
        Assert.assertNotNull(result);
        Assert.assertEquals(ResultCode.SUCCESS.getCode(),result.getCode());
        Assert.assertEquals(ResultCode.SUCCESS.getMsg(),result.getMsg());

        //测试添加返回数据Data是否正确
        Result<String> dataResult = Result.createBySuccess("test");
        Assert.assertEquals(ResultCode.SUCCESS.getCode(),dataResult.getCode());
        Assert.assertEquals("test",dataResult.getData());

        //测试修改返回信息是否正确
        Result<String> msgResult = Result.createBySuccessWithMsg("test");
        Assert.assertEquals(ResultCode.SUCCESS.getCode(),msgResult.getCode());
        Assert.assertEquals("test",msgResult.getMsg());

        //测试成功信息
        Result<String> result_4 = Result.createBySuccess("test","test");
        Assert.assertEquals(ResultCode.SUCCESS.getCode(),result_4.getCode());
        Assert.assertEquals("test",result_4.getMsg());
        Assert.assertEquals("test",result_4.getData());
    }

    @Test
    public void createByError() throws Exception {
        //测试基本返错误码、错误信息是否正确
        Result result = Result.createByError();
        Assert.assertNotNull(result);
        Assert.assertEquals(ResultCode.ERROR.getCode(),result.getCode());
        Assert.assertEquals(ResultCode.ERROR.getMsg(),result.getMsg());

        //测试返错误码、错误信息是否正确
        Result result_1 = Result.createByError("200000","User Error");
        Assert.assertNotNull(result_1);
        Assert.assertEquals("200000",result_1.getCode());
        Assert.assertEquals("User Error",result_1.getMsg());

    }

}