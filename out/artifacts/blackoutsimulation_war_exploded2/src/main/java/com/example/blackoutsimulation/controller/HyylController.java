//package com.example.blackoutsimulation.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.example.blackoutsimulation.entity.BetHistory;
//import com.example.blackoutsimulation.entity.BetTickets;
//import com.example.blackoutsimulation.service.BetService;
//import com.example.blackoutsimulation.utils.HttpRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RequestMapping("/hyyl")
//@RestController
//public class HyylController {
//    private BetService betService;
//
//    @Value("${common.hycookies}")
//    private String hycookies;
//
//    @GetMapping("/getTicketsList")
//    @ResponseBody
//    @CrossOrigin
//    public JSONArray getTicketsList() {
//        String res = HttpRequest.doGet("https://auth.aahygj.com/d/mmbetlist", hycookies);
//        JSONArray tickets = new JSONArray();
//        if (!StringUtils.isEmpty(res)) {
//            JSONObject jsonObject = JSON.parseObject(res);
//            tickets = jsonObject.getJSONArray("Tickets");
//            return tickets;
//        } else {
//            return null;
//        }
//    }
//
//    @GetMapping("/doRegister")
//    @ResponseBody
//    @CrossOrigin
//    public void doRegister(String code) throws ParseException {
//        String url = "https://hyyl180.com/d/member/register";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("UserName", "hh1551");
//        jsonObject.put("Password", "hh1551");
//        jsonObject.put("SecurePassword", "014578");
//        jsonObject.put("SureName", "唐华贵");
//        jsonObject.put("PromoCode", code);
//        jsonObject.put("Mobile", "18877495804");
//        jsonObject.put("QQ", "18877495804");
//        jsonObject.put("Wechat", "18877495804");
//        System.out.println(jsonObject);
//        String res = HttpRequest.doReGisterPost(url,jsonObject);
//        System.out.println(JSONObject.parseObject(res));
//    }
//
//    @GetMapping("/getBetHistory")
//    @ResponseBody
//    @CrossOrigin
//    public Map<String, Object> getBetHistory() {
//        String reshistory = HttpRequest.doPost("https://auth.aahygj.com/d/betHistory", hycookies);
//        JSONArray historyList = null;
//        Map<String, Object> list = new HashMap<>();
//        if (!StringUtils.isEmpty(reshistory)) {
//            JSONObject resObject = JSON.parseObject(reshistory);
//            String TotalWinLose = resObject.getString("TotalWinLose");
//            historyList = resObject.getJSONArray("Tickets");
//            list.put("TotalWinLose", TotalWinLose);
//            list.put("list", historyList);
//            return list;
//        } else {
//            return null;
//        }
//    }
//}
