package com.example.blackoutsimulation.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.blackoutsimulation.dao.PagePayInfo;
import com.example.blackoutsimulation.utils.AlipayUtil;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("支付宝电脑网站支付")
    @CrossOrigin
    public void pagePay(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016102700768995",
              "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHbpltbfGzMfxtX3/N7sIIxQTEuHvhljiST9c3idUEfvVgFbqq/KOFvKjvZz9r8k7RRmDkKFjjImdOUgEYFFhtEC5i9y7QzB6Iukq1kianBo4QBSIZlsb4sTZYioOlilF6HFpnHa3ZbUb/HX7yfRiPQXJ9VdGYp4WUItwz0p7WjkynmmHjQMCPBtGYkNcX+cvOoleh/94+sgqJih3GJoTxo9IfTree6V2+pA3xh2kTd/reqCmWMl0fMtXg3w/VlLPxBC6+HXC/KRPGY3A1s1QE0H8/t/56EX3ivOVT8SVWYL+eoRXw6hHMha3ZDkTbtmXFsZsGfhylUF2DdH5DhDQzAgMBAAECggEATxIKwthUfDfVXYf/pCaFx/RVb6m1dDubMmd1bQ+45Y9wuifjWufP7bZfRIwrGSF5HoBI6i0VEPkS/HqG9IUciiFgQty+Mwq7EHrMJDFOIer3aGKgooSlXvTkFzbT+05b/44+SaP6yboJ7BxFuN7gXewU+JyN/Xht/c3UgLeBQo91gZ6mMnGdVZ97YFm95E+muBxBx8pKLlf6Y5PSkgm8j+nh4O2Yby6yWs+mu1DecNLSxYdDFyZC+XORt3GqJWHE9lbN6KD0miK6KbEGM/c9zH3WwWlpvY7J/7UvJ+am9gyzqvlcFML/6Cwv9Y63rcJ97/4/RTallYrTByoKCi9YAQKBgQDrdm/H5qc4nc+pSgkHyfC2lwAGly9GUHZBHItBstJ42JVzGStG35PvKr5UIjaPFjyBgpQjx/9zMPEgcmxQx67ODSFeW8QobGQ3hmYfN53zvt4KBcxcY6YgdYiXuVhniHoaGF1eWgBB964ewfvEjlks5e0BhTghSUkbF70wDLA9ewKBgQDY06XLoZEttBWSPUw9qzjlODw6nj4hdhgTPjIH1yXftQ1ABVr+VfaFLv2r1HFizNNmNBj7asvgymixOXMLwNrC+Q0EuYhJAJGWIzCMh5EaPw3awDllK+5xdgo5xUI19HxN9g6un29IeFk2e1dqRJgQBaNYhmrDHwT31u/ige16qQKBgCTKXKzfXUF7ZvcMmlydMP9WmKpu9PQHnnKOAzRAvKIlNTTGufxY8sRr6VE8B0pULyANRxhQJ5nYC9UGC+aTTLzlUFFuBThLt1z1ov89sXBkYk0umr/U0iBAPRd33lhO+sUZCX20klW8XXhw1uXfWA+r3VxfdGW/Da0uCGF5mhABAoGBAJ/mva9FcHY/B2V4lSAphGTuquQjzPgKSkjupiDIFqM6txHOoUNNi+a+N88fdZsCKKiHpSDMqpwVSU3haylwySPeXS5t0Mbdh4AsZWJNm8G6XHJFLk6hLlA4V+Qwx9pYec9YtY0sAPp13nziSx4Qhf/S2JqxTkylNJm9xdzKKqM5AoGATrSRWmjt0Jx/U1bNCCA5maXndJFjt4dJRFbXupYr6T+yYG50QuPoKkkAcEdO+cxJ7C0PQe8JFhB1vz1QuH1OkOaO9wsjeARnRXFAUly3tGweyZMuoj5jyUfngG2A0XsatHjFlKKkoyJL92Ap35tlI5kYCKGv208BtqdcfWg6Seo=",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr0XSOk5TBwg95AHjlb2u8h7gIanusUh7TIXJ+A0x8YmFJgEVRVUe+i/ZtDWRM0U4KsYDDKiI1+0ctQ3F0fEE1r4Ku7T1qfXfpOqODI5Gl61TuupEL8diIJdhoZajm3khEjdutqmH1gc3oUOoSAnA37BZlvPjTEnM/NzQwGFKqjipDjU9KeMSFHxeetdaSPW1n6XrMrjQJpZtLuZlp5yVf5wBzEbeImo2HiZh6x/lVTHYwKTuC34xOE8QVNTaBA88/uxo+2uK5UxqbXoXOaG5O1x7D09jXwOylQrDqw93GZReOuR1s7febj0gPqqNw5HTPlQZ3/GQ6jqkt8fzJhHepwIDAQAB","RSA2"
        );

        //发起电脑网页
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //页面跳转同步通知页面路径
        alipayRequest.setReturnUrl("");
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl("");
        //封装参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150329010101001\"," +
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


        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(result);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

    }

    @GetMapping("/pay/mobilePay")
    @ApiOperation("支付宝手机网站支付")
    @CrossOrigin
    public void page(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016102700768995",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHbpltbfGzMfxtX3/N7sIIxQTEuHvhljiST9c3idUEfvVgFbqq/KOFvKjvZz9r8k7RRmDkKFjjImdOUgEYFFhtEC5i9y7QzB6Iukq1kianBo4QBSIZlsb4sTZYioOlilF6HFpnHa3ZbUb/HX7yfRiPQXJ9VdGYp4WUItwz0p7WjkynmmHjQMCPBtGYkNcX+cvOoleh/94+sgqJih3GJoTxo9IfTree6V2+pA3xh2kTd/reqCmWMl0fMtXg3w/VlLPxBC6+HXC/KRPGY3A1s1QE0H8/t/56EX3ivOVT8SVWYL+eoRXw6hHMha3ZDkTbtmXFsZsGfhylUF2DdH5DhDQzAgMBAAECggEATxIKwthUfDfVXYf/pCaFx/RVb6m1dDubMmd1bQ+45Y9wuifjWufP7bZfRIwrGSF5HoBI6i0VEPkS/HqG9IUciiFgQty+Mwq7EHrMJDFOIer3aGKgooSlXvTkFzbT+05b/44+SaP6yboJ7BxFuN7gXewU+JyN/Xht/c3UgLeBQo91gZ6mMnGdVZ97YFm95E+muBxBx8pKLlf6Y5PSkgm8j+nh4O2Yby6yWs+mu1DecNLSxYdDFyZC+XORt3GqJWHE9lbN6KD0miK6KbEGM/c9zH3WwWlpvY7J/7UvJ+am9gyzqvlcFML/6Cwv9Y63rcJ97/4/RTallYrTByoKCi9YAQKBgQDrdm/H5qc4nc+pSgkHyfC2lwAGly9GUHZBHItBstJ42JVzGStG35PvKr5UIjaPFjyBgpQjx/9zMPEgcmxQx67ODSFeW8QobGQ3hmYfN53zvt4KBcxcY6YgdYiXuVhniHoaGF1eWgBB964ewfvEjlks5e0BhTghSUkbF70wDLA9ewKBgQDY06XLoZEttBWSPUw9qzjlODw6nj4hdhgTPjIH1yXftQ1ABVr+VfaFLv2r1HFizNNmNBj7asvgymixOXMLwNrC+Q0EuYhJAJGWIzCMh5EaPw3awDllK+5xdgo5xUI19HxN9g6un29IeFk2e1dqRJgQBaNYhmrDHwT31u/ige16qQKBgCTKXKzfXUF7ZvcMmlydMP9WmKpu9PQHnnKOAzRAvKIlNTTGufxY8sRr6VE8B0pULyANRxhQJ5nYC9UGC+aTTLzlUFFuBThLt1z1ov89sXBkYk0umr/U0iBAPRd33lhO+sUZCX20klW8XXhw1uXfWA+r3VxfdGW/Da0uCGF5mhABAoGBAJ/mva9FcHY/B2V4lSAphGTuquQjzPgKSkjupiDIFqM6txHOoUNNi+a+N88fdZsCKKiHpSDMqpwVSU3haylwySPeXS5t0Mbdh4AsZWJNm8G6XHJFLk6hLlA4V+Qwx9pYec9YtY0sAPp13nziSx4Qhf/S2JqxTkylNJm9xdzKKqM5AoGATrSRWmjt0Jx/U1bNCCA5maXndJFjt4dJRFbXupYr6T+yYG50QuPoKkkAcEdO+cxJ7C0PQe8JFhB1vz1QuH1OkOaO9wsjeARnRXFAUly3tGweyZMuoj5jyUfngG2A0XsatHjFlKKkoyJL92Ap35tlI5kYCKGv208BtqdcfWg6Seo=",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr0XSOk5TBwg95AHjlb2u8h7gIanusUh7TIXJ+A0x8YmFJgEVRVUe+i/ZtDWRM0U4KsYDDKiI1+0ctQ3F0fEE1r4Ku7T1qfXfpOqODI5Gl61TuupEL8diIJdhoZajm3khEjdutqmH1gc3oUOoSAnA37BZlvPjTEnM/NzQwGFKqjipDjU9KeMSFHxeetdaSPW1n6XrMrjQJpZtLuZlp5yVf5wBzEbeImo2HiZh6x/lVTHYwKTuC34xOE8QVNTaBA88/uxo+2uK5UxqbXoXOaG5O1x7D09jXwOylQrDqw93GZReOuR1s7febj0gPqqNw5HTPlQZ3/GQ6jqkt8fzJhHepwIDAQAB","RSA2"
        );
        // 发起App支付请求
//        AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150320020101002\"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();


    }
}

