package com.example.blackoutsimulation.config;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ResponseCode {

    FAIL(100, "失败", null),
    SYSTEM_ERROR(101, "系统错误", null),
    PARAMETER_ERROR(102, "参数错误", null),
    PARAMETER_VALUE_IS_NULL(103, "参数值为空", null),
    SUCCESS(200, "成功", "请求正确，查询结果为有效数据，属于有效查询"),
    CREATED_SUCCESSFULLY(201, "创建成功", "请求正确，资源被正确的创建，如提交申请被成功受理或正确创建用户等"),
    REQUEST_SUCCESSFUL(202, "请求成功", "请求正确，服务器已接受请求，但是结果正在处理中，主要用于异步请求"),
    INVALID_REQUEST(203, "无效请求", "请求正确，但未查询到有效数据，属于无效查询"),
    NO_AUTHORIZATION(300, "认证失败，无授权信息", "没有提供认证信息，请求的时候没有带上Token"),
    INVALID_AUTHORIZATION(301, "认证失败，授权无效", "授权信息未通过认证，如Token过时失效、Token信息不匹配等"),
    DATA_NOT_AUTHORIZED(302, "认证失败，数据未授权", "数据权限认证失败，查询的数据内容没有企业授权等"),
    DATA_TO_MANY(303,"多个参数，系统无法选取","参数在数据库查询有多个，程序无法确定");

    int responseCode;
    String describe;
    String remarks;

    ResponseCode(int responseCode, String describe, String remarks) {
        this.responseCode = responseCode;
        this.describe = describe;
        this.remarks = remarks;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getDescribe() {
        return describe;
    }

    public String getRemarks() {
        return remarks;
    }

}
