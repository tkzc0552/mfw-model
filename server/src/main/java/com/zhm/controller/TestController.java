package com.zhm.controller;

import com.zhm.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 赵红明 on 2020/1/2.
 */
@Api(value = "测试",description = "这是一个测试的接口，调试是否可用")
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    @ApiOperation(value = "测试能否返回(不用拦截的)",notes = "这是备注",httpMethod = "GET")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result test(){
        return Result.sendSuccess("调试通过");
    }

}
