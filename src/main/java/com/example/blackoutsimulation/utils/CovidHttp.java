package com.example.blackoutsimulation.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CovidHttp {
    public static String CovidPost(String URL, JSONObject jsonObject) throws ParseException {
        String result;
        try {
            PostMethod postMethod = null;
            postMethod = new PostMethod(URL);
            postMethod.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            postMethod.setRequestHeader("sec-ch-ua-mobile", "?1");
            postMethod.setRequestHeader("Sec-Fetch-Dest", "empty");
            postMethod.setRequestHeader("Sec-Fetch-Mode", "cors");
            postMethod.setRequestHeader("Sec-Fetch-Site", "same-origin");
            postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Mobile Safari/537.36");
            postMethod.setRequestHeader("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");

          //参数设置，需要注意的就是里边不能传NULL，要传空字符串

//            NameValuePair[] data = {
//                    new NameValuePair("groupArea", groupArea),
//                    new NameValuePair("groupStreet", groupStreet)
//            };

//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("groupArea",groupArea);
//            jsonObject.put("groupStreet",groupStreet);

            postMethod.setRequestBody(String.valueOf(jsonObject));
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            int response = httpClient.executeMethod(postMethod); // 执行POST方法
            result = postMethod.getResponseBodyAsString();
        } catch (Exception e) {
            // logger.info("请求异常"+e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }


    public static String getSzCovidPost(String URL, String params) throws ParseException {
        String result;
        try {
            PostMethod postMethod = null;
            postMethod = new PostMethod(URL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            postMethod.setRequestHeader("otn", "w8oyrdQxZmb2eugv5euWG+dBS4CFFM8/Ia0Wk1anZDRJ/0ywcoRcsZf17133fcYuDndmg2x+r22nooVgmQRVMDAtHpJdbU5ekndAiUQQlqAOiJ8E3H5QphHxauhhOPw6");
            postMethod.setRequestHeader("reservationtoken", "0fc3f73fae974c32810de43ae67f1d4d");
            postMethod.setRequestHeader("sec-fetch-dest", "empty");
            postMethod.setRequestHeader("Sec-fetch-mode", "cors");
            postMethod.setRequestHeader("Sec-fetch-site", "same-origin");
            postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
            postMethod.setRequestHeader("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
            postMethod.setRequestHeader("selfappid", "wx262c00273afab5c7");
            postMethod.setRequestHeader("token", "-t-C68B3lsma_SXW9pZmICmeALyU5IGQB8uYWxkf4uRWIyTwepsdUSikmhtS2O0_Bw3");
            postMethod.setRequestHeader("ybm", "Vu6rQOR+JL90rcj3CcR6W+90bt01KaIDHfG1l9aK3yg=");
            postMethod.setRequestHeader("appid", "app569d18f5");
            postMethod.setRequestHeader("origin", "https://xgsz.szcdc.net");

            //参数设置，需要注意的就是里边不能传NULL，要传空字符串

            NameValuePair[] data = {
                    new NameValuePair("params", params),

            };


            postMethod.setRequestBody(data);
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            int response = httpClient.executeMethod(postMethod); // 执行POST方法
            result = postMethod.getResponseBodyAsString();
        } catch (Exception e) {
            // logger.info("请求异常"+e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
