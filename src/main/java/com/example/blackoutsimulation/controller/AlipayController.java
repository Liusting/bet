package com.example.blackoutsimulation.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.blackoutsimulation.utils.AlipayUtil;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@Api("支付宝接口")
public class AlipayController {

    @GetMapping("/pay/alipay")
    @ApiOperation("支付宝当面付获取二维码")
    public JSONObject test_trade_precreate() throws Exception {
        return AlipayUtil.test_trade_precreate();
    }


    @GetMapping("/pay/pagePay")
    @ApiOperation("支付宝网站支付111")
    @CrossOrigin
    public String pagePay(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016102700768995",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3x+oCvIaQYcVqkhtVxVkVZseQI62GLWgIGFptSFqe76RXoBGydHK7Y7HSMLKJ0u30nwaUVw+L3tbnJR2os1LtghI7ptCzOCRy7qUvWG4xwCeUwXK+ifNZ4MMuHmb3ALDqKKU+duN3TLjV2Om5sNtF9BNvRyjztJqSs20uA+5M3DOKiv25a3xjTQzAh0XugnrIRmbV+tkIvNRNyv8J3W5ag60PBrKQzL+70yGFUEAleO1sUioqrJssssFPqwpnlKL6WkXK7QHEL9v9z55OJGh1EvFcZPyXGXpXoHPISdwPdHPCNMEHmuDm1jijIKUjuxMRlmT3ELd5zALjpDUb3ZS1AgMBAAECggEBAJULfo8lkgQ6Jr7dK2oOGMcwiZwuMyZkYMBf78Y9LbFELIYb/P47TmkG20nZ0JHSDMWcyts3GBaZbLQc98a/V/vixyK1kQAoAfdr729MSmHysqcDlClO+5rim6glY9/iIZhlyk20YZl5EVXtCvBbrBk5byGKFn4+9DURavQsn/CYK8UcUmENxlkZ2YPvxUo9S6JrQDn4GjmZ7qg4ZJe4Q1fu3YgcCgnXSEskKiYaBpRfznqHbzsWsQMituVrDaVERKQdh47fuoKZYghdngjlF7fWbDWOsOCaL6t0uatgKJ9CJK8NNjqv/FyT425AWw8gsT7Vf67QVe2niCsWTvX/u4ECgYEA7RjWcSYCcFazOvaicI9bEYa0YqhrNYX7x/njlTHfL5pZbJf2pSmfzniOLaJGdtq9BuZZOziaFulw4l+r6Feww5Gz9ZMx+4Y8mCgQKEkVYp3XdE8oLwpI0pa+pZOxy1YHyVCRNYdOWubeouqZJulRtkiZoM72Qa9EKBNuch4p1BECgYEAxm7kV2nmh+hXQubVzz63aFUt25QB9qzDaqoCYdt56PAbqQSpcwhv6UPnJhpet9qkJpdwCdHIUmmFd1ySWc6vGloaU5ZcLgGDOpQoX5J3pSYRsK9vf/peCgg+BJMCTKA1/4cGXWg+drMkT7TogvD11KlvcirvdsYVMVDRxQ0QSmUCgYEA51oeHah2JsIi/70bwnPP7CkiKUan3kFP7tFCphlU67FT4ufhr0YPfrwaGCNJWL6WRn5MwcG4cfT1xp6OMxgmDp9FF7K6yfExfVNSeMzfhiv4ybBXyJbjekHH4+9bRMs8q87m8nGGHqM6yYPbT6HJrvHmnoh0uKFaAzT6x3XxYNECgYB8Tzv9BvrEaWlBIBsRmdiMI9B2nmIchl5lk9uqMjRNGR465BDDX6W8HC3SUPLFetMa2gP2ItW7yx8xh2ynljNb7tZPRwK4T3pGWEpylO8fJpoudbkEE3SWFFPFydLG7hbl3VC/c26uLcuxo0OFxC4862hFvWAGqFDedCL1rxllgQKBgGwK8FhWXR5Q6fiNCks+v1fsuX0TosixGHk1tMTJZOTWQP4dLofJ8NS6GpSH0cAn9Zifr6CQ2lvovM/AmivBG8vJXzc+9ROMDp8wys5hKOIHVzlf8YOq1wslP4fjrI+oLTiByHm3OndsI2jpC7ZIgeBl1/J1PTpr6TxU+jeyxpSN",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt8fqAryGkGHFapIbVcVZFWbHkCOthi1oCBhabUhanu+kV6ARsnRyu2Ox0jCyidLt9J8GlFcPi97W5yUdqLNS7YISO6bQszgkcu6lL1huMcAnlMFyvonzWeDDLh5m9wCw6iilPnbjd0y41djpubDbRfQTb0co87SakrNtLgPuTNwzior9uWt8Y00MwIdF7oJ6yEZm1frZCLzUTcr/Cd1uWoOtDwaykMy/u9MhhVBAJXjtbFIqKqybLLLBT6sKZ5Si+lpFyu0BxC/b/c+eTiRodRLxXGT8lxl6V6BzyEncD3RzwjTBB5rg5tY4oyClI7sTEZZk9xC3ecwC46Q1G92UtQIDAQAB",
                "RSA2"
        );

        //2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //页面跳转同步通知页面路径
        alipayRequest.setReturnUrl("");
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl("");
        //封装参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");
        String result = "";
        try {
            //3、请求支付宝进行付款，并获取支付结果
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;

//        httpResponse.setContentType("text/html;charset=utf-8");
//        httpResponse.getWriter().write(result);//直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();

    }

    @GetMapping("/pay/netPay")
    @ApiOperation("支付宝网站支付")
    @CrossOrigin
    public JSONObject page(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016102700768995",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3x+oCvIaQYcVqkhtVxVkVZseQI62GLWgIGFptSFqe76RXoBGydHK7Y7HSMLKJ0u30nwaUVw+L3tbnJR2os1LtghI7ptCzOCRy7qUvWG4xwCeUwXK+ifNZ4MMuHmb3ALDqKKU+duN3TLjV2Om5sNtF9BNvRyjztJqSs20uA+5M3DOKiv25a3xjTQzAh0XugnrIRmbV+tkIvNRNyv8J3W5ag60PBrKQzL+70yGFUEAleO1sUioqrJssssFPqwpnlKL6WkXK7QHEL9v9z55OJGh1EvFcZPyXGXpXoHPISdwPdHPCNMEHmuDm1jijIKUjuxMRlmT3ELd5zALjpDUb3ZS1AgMBAAECggEBAJULfo8lkgQ6Jr7dK2oOGMcwiZwuMyZkYMBf78Y9LbFELIYb/P47TmkG20nZ0JHSDMWcyts3GBaZbLQc98a/V/vixyK1kQAoAfdr729MSmHysqcDlClO+5rim6glY9/iIZhlyk20YZl5EVXtCvBbrBk5byGKFn4+9DURavQsn/CYK8UcUmENxlkZ2YPvxUo9S6JrQDn4GjmZ7qg4ZJe4Q1fu3YgcCgnXSEskKiYaBpRfznqHbzsWsQMituVrDaVERKQdh47fuoKZYghdngjlF7fWbDWOsOCaL6t0uatgKJ9CJK8NNjqv/FyT425AWw8gsT7Vf67QVe2niCsWTvX/u4ECgYEA7RjWcSYCcFazOvaicI9bEYa0YqhrNYX7x/njlTHfL5pZbJf2pSmfzniOLaJGdtq9BuZZOziaFulw4l+r6Feww5Gz9ZMx+4Y8mCgQKEkVYp3XdE8oLwpI0pa+pZOxy1YHyVCRNYdOWubeouqZJulRtkiZoM72Qa9EKBNuch4p1BECgYEAxm7kV2nmh+hXQubVzz63aFUt25QB9qzDaqoCYdt56PAbqQSpcwhv6UPnJhpet9qkJpdwCdHIUmmFd1ySWc6vGloaU5ZcLgGDOpQoX5J3pSYRsK9vf/peCgg+BJMCTKA1/4cGXWg+drMkT7TogvD11KlvcirvdsYVMVDRxQ0QSmUCgYEA51oeHah2JsIi/70bwnPP7CkiKUan3kFP7tFCphlU67FT4ufhr0YPfrwaGCNJWL6WRn5MwcG4cfT1xp6OMxgmDp9FF7K6yfExfVNSeMzfhiv4ybBXyJbjekHH4+9bRMs8q87m8nGGHqM6yYPbT6HJrvHmnoh0uKFaAzT6x3XxYNECgYB8Tzv9BvrEaWlBIBsRmdiMI9B2nmIchl5lk9uqMjRNGR465BDDX6W8HC3SUPLFetMa2gP2ItW7yx8xh2ynljNb7tZPRwK4T3pGWEpylO8fJpoudbkEE3SWFFPFydLG7hbl3VC/c26uLcuxo0OFxC4862hFvWAGqFDedCL1rxllgQKBgGwK8FhWXR5Q6fiNCks+v1fsuX0TosixGHk1tMTJZOTWQP4dLofJ8NS6GpSH0cAn9Zifr6CQ2lvovM/AmivBG8vJXzc+9ROMDp8wys5hKOIHVzlf8YOq1wslP4fjrI+oLTiByHm3OndsI2jpC7ZIgeBl1/J1PTpr6TxU+jeyxpSN",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt8fqAryGkGHFapIbVcVZFWbHkCOthi1oCBhabUhanu+kV6ARsnRyu2Ox0jCyidLt9J8GlFcPi97W5yUdqLNS7YISO6bQszgkcu6lL1huMcAnlMFyvonzWeDDLh5m9wCw6iilPnbjd0y41djpubDbRfQTb0co87SakrNtLgPuTNwzior9uWt8Y00MwIdF7oJ6yEZm1frZCLzUTcr/Cd1uWoOtDwaykMy/u9MhhVBAJXjtbFIqKqybLLLBT6sKZ5Si+lpFyu0BxC/b/c+eTiRodRLxXGT8lxl6V6BzyEncD3RzwjTBB5rg5tY4oyClI7sTEZZk9xC3ecwC46Q1G92UtQIDAQAB",
                "RSA2"
        );
        // 发起App支付请求
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        // 订单描述
        model.setBody("我是测试数据");
        // 订单标题
        model.setSubject("App支付测试Java");
        // 商户订单号 就是商户后台生成的订单号
        model.setOutTradeNo("201503200101010011");
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天 (屁股后面的字母一定要带，不然报错)
        model.setTimeoutExpress("30m");
        // 订单总金额 ，默认单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        model.setTotalAmount("0.01");
        // 销售产品码 不必填
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        // request.setNotifyUrl("商户外网可以访问的异步地址，不写就是不回调");
        // 通过api的方法请求阿里接口获得反馈
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        String result = response.getBody();
        //这里以上都是支付宝的，接下来是我的
        //接下来是一系列的字符串操作，总之就是给支付宝返回的result页面的按钮属性设置为非hidden，并且添加了一些好看的属性，然后取出来<script>标签（因为前端用v-html不能显示<script>）最后将整个改造的result发给前端，就有了上面的前端将接收的内容写入sessionStorage的操作
        String befAction = result;
        StringBuffer aftAction = new StringBuffer(befAction);
        aftAction = aftAction.reverse();
        String midAction = aftAction.substring(68);
        aftAction = new StringBuffer(midAction).reverse();
        aftAction = aftAction.append(" width: 200px;  padding:8px;  background-color: #428bca;  border-color: #357ebd; color: #fff;  -moz-border-radius: 10px;  -webkit-border-radius: 10px;  border-radius: 10px;  -khtml-border-radius: 10px;text-align: center;  vertical-align: middle;  border: 1px solid transparent;  font-weight: 900;  font-size:125% \"> </form>");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("formaction", aftAction);
        jsonObject.put("message", "成功");
        jsonObject.put("code", "200");
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return jsonObject;


    }
}

