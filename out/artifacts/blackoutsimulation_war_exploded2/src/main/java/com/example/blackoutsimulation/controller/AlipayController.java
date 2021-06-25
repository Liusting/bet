package com.example.blackoutsimulation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.blackoutsimulation.utils.AlipayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("支付宝接口")
public class AlipayController {

    @GetMapping("/pay/alipay")
    @ApiOperation("支付宝当面付获取二维码")
    public JSONObject test_trade_precreate() {
       return AlipayUtil.test_trade_precreate();
    }
}
