package com.example.blackoutsimulation.service;


import com.alibaba.fastjson.JSONArray;

import java.text.ParseException;

public interface BetService {
    //下注
    void Bet(String betContentList) throws ParseException;
    //获取最新一期的下注号数
    JSONArray getBetNumber(String newcookies);
    //获取最新的开奖结果
    JSONArray getNewLastHistory(String newcookies);
    //注册
    void doGister() throws ParseException;
    //登录获取cookies
    String getCookies(String UserName,String Password) throws ParseException;
}
