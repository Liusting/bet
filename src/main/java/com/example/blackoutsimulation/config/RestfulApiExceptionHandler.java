package com.example.blackoutsimulation.config;

import com.alibaba.fastjson.JSON;
import com.example.blackoutsimulation.entity.ResponseMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class RestfulApiExceptionHandler {

    @SneakyThrows
    @ExceptionHandler()
    @ResponseBody
    public ResponseMessage requestExceptionHandler(HttpServletRequest request, NullPointerException exception) {
        log.error("", exception);
        int responseCode;
        String infoResult;
        String infoDetail;
        responseCode = ResponseCode.INVALID_REQUEST.getResponseCode();
        infoResult = "Invalid Query";
        infoDetail = "无效查询";
        return generateResponseMessage(request, responseCode, infoResult, infoDetail);

    }

    @ExceptionHandler()
    @ResponseBody
    public ResponseMessage requestExceptionHandler(HttpServletRequest request, IllegalArgumentException exception) {
        log.error("", exception);
        int responseCode;
        String infoResult;
        String infoDetail;
        responseCode = ResponseCode.PARAMETER_ERROR.getResponseCode();
        infoResult = "Parameter error";
        infoDetail = "参数错误";

        return generateResponseMessage(request, responseCode, infoResult, infoDetail);

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        log.error("", exception);
        int responseCode;
        String infoResult;
        String infoDetail;
        if (exception.getMessage().equals(ResponseCode.PARAMETER_VALUE_IS_NULL.getDescribe())) {
            responseCode = ResponseCode.PARAMETER_VALUE_IS_NULL.getResponseCode();
            infoResult = "Parameter is null";
            infoDetail = "参数为空";
        } else if (exception.getMessage().equals(ResponseCode.PARAMETER_ERROR.getDescribe())) {
            responseCode = ResponseCode.PARAMETER_ERROR.getResponseCode();
            infoResult = "Parameter error";
            infoDetail = "参数错误";
        } else if (exception.getMessage().equals(ResponseCode.NO_AUTHORIZATION.getDescribe())) {
            responseCode = ResponseCode.NO_AUTHORIZATION.getResponseCode();
            infoResult = "Authentication Fail";
            infoDetail = "认证失败，无授权信息";
        }else if (exception.getMessage().equals(ResponseCode.INVALID_AUTHORIZATION.getDescribe())) {
            responseCode = ResponseCode.INVALID_AUTHORIZATION.getResponseCode();
            infoResult = "Authentication Fail";
            infoDetail = "认证失败，授权无效";
        } else if (exception.getMessage().equals(ResponseCode.DATA_NOT_AUTHORIZED.getDescribe())) {
            responseCode = ResponseCode.DATA_NOT_AUTHORIZED.getResponseCode();
            infoResult = "Date Not Authorized";
            infoDetail = "认证失败，数据未授权";
        }else if (exception.getMessage().equals(ResponseCode.INVALID_REQUEST.getDescribe())) {
            responseCode = ResponseCode.INVALID_REQUEST.getResponseCode();
            infoResult = "Invalid Query";
            infoDetail = "无效查询";
        } else {
            responseCode = ResponseCode.FAIL.getResponseCode();
            infoResult = "fail";
            infoDetail = "失败";
        }
        return generateResponseMessage(request, responseCode, infoResult, infoDetail);

    }

    public ResponseMessage generateResponseMessage(HttpServletRequest request, int responseCode, String infoResult, String infoDetail) {
        ResponseMessage responseMessage = new ResponseMessage();
        ResponseMessage.Info info = new ResponseMessage.Info();
        info.setResult(infoResult);
        info.setDetail(infoDetail);
        responseMessage.setInfo(info);
        String operate = (request.getRequestURI()).substring(5);
        String operate2 = (new StringBuilder()).append("get").append(Character.toUpperCase(operate.charAt(0))).append(operate.substring(1)).toString();
        responseMessage.setOperate(operate2);
        responseMessage.setResponseCode(responseCode);
        return responseMessage;
    }

}