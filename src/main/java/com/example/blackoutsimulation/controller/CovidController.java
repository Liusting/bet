package com.example.blackoutsimulation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blackoutsimulation.dao.BetTicketsDao;
import com.example.blackoutsimulation.entity.Covid;
import com.example.blackoutsimulation.service.CovidService;
import com.example.blackoutsimulation.utils.CovidHttp;
import com.example.blackoutsimulation.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/getCovidMsg")
@RestController
public class CovidController {

    @Autowired
    private CovidService covidService;

    @GetMapping("/getAreaMsg")
    @ResponseBody
    @CrossOrigin
    public JSONObject getAreaMsg(String id,String groupArea,String groupStreet) throws ParseException {

        JSONObject requestMsg = new JSONObject();
        requestMsg.put("groupArea",groupArea);
        requestMsg.put("groupStreet",groupStreet);

        String res = CovidHttp.CovidPost("https://fsservice.wjj.foshan.gov.cn/fw2/foying/wechatpublic/wx/userBooking/getOrganizeByGroupArea",requestMsg);
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray jsonArray = jsonObject.getJSONArray("entityList");

        JSONObject result = new JSONObject();
        result.put("entityList",jsonArray);
        result.put("groupArea",groupArea);
        result.put("groupStreet",groupStreet);
        return result;

    }

    @PostMapping("/getAreaMsgpost")
    @ResponseBody
    @CrossOrigin
    public JSONObject getAreaMsgPost(@RequestBody Covid covid,@RequestParam(required = true) String access_token, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        covidService.checkToken(request,response);

        JSONObject requestMsg = new JSONObject();
        requestMsg.put("groupArea",covid.getGroupArea());
        requestMsg.put("groupStreet",covid.getGroupStreet());

        String res = CovidHttp.CovidPost("https://fsservice.wjj.foshan.gov.cn/fw2/foying/wechatpublic/wx/userBooking/getOrganizeByGroupArea",requestMsg);
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray jsonArray = jsonObject.getJSONArray("entityList");

        JSONObject result = new JSONObject();
        result.put("entityList",jsonArray);
        result.put("groupArea",covid.getGroupArea());
        result.put("groupStreet",covid.getGroupStreet());
        return result;

    }

    @GetMapping("/getUserInfo")
    @ResponseBody
    @CrossOrigin
    public JSONObject getUserInfo() throws ParseException {

        JSONObject requestMsg = new JSONObject();
        requestMsg.put("expara","e3de1598e5f1f1d004e0f8ec24dcd51253829139807a46c48bb328721a6d10ea76ec24c2f70aeb99a89d024e5a482195f170b7f739e9d92bbe4d7c7cee557327d71c645764d480905a50c5bbd3e7c642f6af193adc7b2264bd773f2014c852fd89ff730d68e62fdfd65c0c22eb40c5a5ab9af4ac18c9b8cbd8e533faba24f47fcc161be311dd1d0b3598456c89cf488e");
        requestMsg.put("openId","omPC31H6N51UY5JVjTVKfAK3vlCA");
        requestMsg.put("orderChannel","ch1");
        String res = CovidHttp.CovidPost("https://fsservice.wjj.foshan.gov.cn/fw2/foying/wechatpublic/wx/user/getUserInfo",requestMsg);
        JSONObject jsonObject = JSON.parseObject(res);
        return jsonObject;

    }

    @GetMapping("/getScheduleByDate")
    @ResponseBody
    @CrossOrigin
    public JSONObject getScheduleByDate(String baseOrganizeID,String scheduleDate) throws ParseException {
        JSONObject requestMsg = new JSONObject();
        requestMsg.put("baseOrganizeID",baseOrganizeID);
        requestMsg.put("scheduleDate",scheduleDate);
        String res = CovidHttp.CovidPost("https://fsservice.wjj.foshan.gov.cn/fw2/foying/wechatpublic/wx/userBooking/getScheduleByDate", requestMsg);
        JSONObject jsonObject = JSON.parseObject(res);
        return jsonObject;
    }


    @GetMapping("/getSzCovid")
    @ResponseBody
    @CrossOrigin
    public JSONObject getSzCovid(String baseOrganizeID,String scheduleDate) throws ParseException {
        JSONObject requestMsg = new JSONObject();
        String params = "BEvehIahEMZGIT3yW02gFJXpAp9xDtUd9FG4ykCnJ3S/915C56Zmprjq0EQPd7m61vYVwkA53kRpLfDxzyrUD9eWE30ETdeWMlgqwaLavuotXYLZXvSMjtJD7wch1TbaAjGw3B/3wcfZynBeP/hGfw==";
        String res = CovidHttp.getSzCovidPost("https://xgsz.szcdc.net/crmobile/outpatient/nearby",params);
        JSONObject jsonObject = JSON.parseObject(res);
        return jsonObject;
    }



}
