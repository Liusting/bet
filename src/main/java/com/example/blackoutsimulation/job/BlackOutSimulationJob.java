package com.example.blackoutsimulation.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blackoutsimulation.dao.BetTicketsDao;
import com.example.blackoutsimulation.entity.*;
import com.example.blackoutsimulation.service.BetService;
import com.example.blackoutsimulation.utils.GetNumberWhereString;
import com.example.blackoutsimulation.utils.HttpRequest;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Local;
import org.quartz.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@DisallowConcurrentExecution
public class BlackOutSimulationJob implements Job {
    private Logger log;
    private BetTicketsDao betTicketsDao;
    private BetService betService;
    private String cookies;
    private String bethistory;
    private String beturl;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        this.betTicketsDao = (BetTicketsDao) dataMap.get("betTicketsDao");
        this.betService = (BetService) dataMap.get("betService");
        this.cookies = (String) dataMap.get("cookies");
        this.bethistory = (String) dataMap.get("bethistory");
        this.beturl = (String) dataMap.get("beturl");
        try {


        String newcookies=null;
        //cookies标识为0的时候，cookies已经过期

              String cookies = betService.getCookies("sy4546","pd4546");



//            betService.doGister();
            /**
             * 获取过去五分钟的开奖结果记录
             */
//            String newcookies = "zz=s2nzjutv1kuswwwjhvoh5w";
            JSONArray tickets = betService.getNewLastHistory("zz="+cookies);
            System.out.println(tickets);
            //当i设置0时，为手动方案，当i设置1时，为自动方案
//            int i = 0;
//            if (i == 0) {
                //手动设置方案
                //从数据库里面找个方案出来投注
//                String startNo = "20201119001";
//                String endNo = "20201119051";
//                List<String> betContentList = betTicketsDao.getContentAuto(startNo, endNo);
//                betService.Bet(betContentList.get(2).toString());
//            } else {
                //自动设置方案
                //获取当前下注的号数记录
//                JSONArray tickets = betService.getBetNumber(newcookies);
//                List<String> list = new ArrayList<String>();
//                if (tickets.size() > 0) {
//                    for (int j = 0; j < tickets.size(); j++) {
//                        //获取下注的号数，倍数，下注金额
//                        String str2 = GetNumberWhereString.getNumberFromString(tickets.getJSONObject(j).getString("BetContent"));
//                        list.add("[" + 11 + "," + "[" + str2 + "]" + "," + "[" + tickets.getJSONObject(j).getString("BetOdds") + "]" + "," + tickets.getJSONObject(j).getString("BetAmount") + "]");
//                    }
//                    betService.Bet(list.toString());
//                }
//            }
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }

    }
}

