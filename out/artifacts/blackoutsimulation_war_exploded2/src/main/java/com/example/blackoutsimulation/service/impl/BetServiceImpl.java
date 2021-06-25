package com.example.blackoutsimulation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blackoutsimulation.dao.BetTicketsDao;
import com.example.blackoutsimulation.entity.BetHistory;
import com.example.blackoutsimulation.entity.BetTickets;
import com.example.blackoutsimulation.service.BetService;
import com.example.blackoutsimulation.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class BetServiceImpl implements BetService {
    @Autowired
    private BetTicketsDao betTicketsDao;

    @Value("${common.seasonNo}")
    private String seasonNoUrl;

    @Value("${common.bet}")
    private String bet;

    @Value("${common.cookies}")
    private String cookies;

    @Value("${common.beturl}")
    private String beturl;

    @Value("${common.bethistory}")
    private String bethistory;

    /**
     * 下注
     */
    @Override
    public void Bet(String betContentList) throws ParseException {
        System.out.println(betContentList);
        //最新一期期数
        String res = HttpRequest.seasonNoPost(seasonNoUrl, cookies);
        JSONObject jsonObject = JSON.parseObject(res);
        String seasonno = jsonObject.getString("seasonno");
        //旧的下注期数
        String oldSeason = betTicketsDao.getSeasonNo();
        if (!oldSeason.equals(seasonno)) {
            //开始投注
//            HttpRequest.doBetPost("https://auth.rycpaa.com/d/bet", cookies, seasonno, betContentList);
            //将最新下注期数存起来
            System.out.println("投注成功");
            betTicketsDao.betSeasonNoInsert(seasonno);
        } else {
            System.out.println("最新期数还没出来！");
        }
    }

    /**
     * 获取最新一期的下注号数
     */
    @Override
    public JSONArray getBetNumber(String newcookies) {
        /**
         * 1.获取当前下注的号码记录
         * 2.获取下注号码后插入数据库，并且执行自动下注
         */
        ZonedDateTime zdt = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String res = HttpRequest.doGet(beturl, newcookies);
        JSONArray tickets = new JSONArray();
        if (!StringUtils.isEmpty(res)) {
            JSONObject jsonObject = JSON.parseObject(res);
            tickets = jsonObject.getJSONArray("Tickets");
            System.out.println("开始获取执行现阶段下注记录");
            if (tickets.size() > 0) {
                betTicketsDao.betTicketsDeleteAll();
                for (int i = 0; i < tickets.size(); i++) {
                    BetTickets betTickets = new BetTickets();
                    betTickets.setId(tickets.getJSONObject(i).getString("TransId"));
                    betTickets.setBetamount(tickets.getJSONObject(i).getString("BetAmount"));
                    betTickets.setBettime(tickets.getJSONObject(i).getString("BetTime"));
                    betTickets.setBetcontent(tickets.getJSONObject(i).getString("BetContent"));
                    betTickets.setBetodds(tickets.getJSONObject(i).getString("BetOdds"));
                    betTickets.setSeasonno(tickets.getJSONObject(i).getString("SeasonNo"));
                    betTickets.setCreatetime(formatter.format(zdt));
                    betTicketsDao.betTicketsInsert(betTickets);
                }
            }
        }
        return tickets;
    }

    /**
     * 获取开奖结果
     * @return
     */
    @Override
    public JSONArray getNewLastHistory(String newcookies) {
        System.out.println(newcookies);
        String reshistory = HttpRequest.doPost(bethistory, newcookies);
        System.out.println(reshistory);
        ZonedDateTime zdt = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        JSONArray historyList=null;
        if (!StringUtils.isEmpty(reshistory)) {
            JSONObject resObject = JSON.parseObject(reshistory);
            String TotalWinLose = resObject.getString("TotalWinLose");
            historyList = resObject.getJSONArray("Tickets");
            System.out.println("开始获取过去五分钟开奖结果记录");
            if (historyList.size() > 0) {
                betTicketsDao.betHistoryDeleteAll();
                for (int j = 0; j < historyList.size(); j++) {
                    BetHistory betHistory = new BetHistory();
                    String number = historyList.getJSONObject(j).getString("draw");
                    String substring = number.substring(0, number.length() - 1);
                    String[] result = substring.split(",");
                    int re = Integer.parseInt(result[0]);
                    int de = Integer.parseInt(result[1]);
                    int rightNumber = re + de;
                    betHistory.setId(historyList.getJSONObject(j).getString("TransId"));
                    betHistory.setBettime(historyList.getJSONObject(j).getString("BetTime"));
                    betHistory.setBetamount(historyList.getJSONObject(j).getString("BetAmount"));
                    betHistory.setBetodds(historyList.getJSONObject(j).getString("BetOdds"));
                    betHistory.setBetcontent(historyList.getJSONObject(j).getString("BetContent"));
                    betHistory.setWinlose(historyList.getJSONObject(j).getString("WinLose"));
                    betHistory.setWincount(historyList.getJSONObject(j).getString("WinAmount"));
                    betHistory.setSeasonno(historyList.getJSONObject(j).getString("SeasonNo"));
                    betHistory.setCreatetime(formatter.format(zdt));
                    betHistory.setNumber(String.valueOf(rightNumber));
                    betHistory.setTotalwinLose(TotalWinLose);
                    betTicketsDao.betHistoryInsert(betHistory);
                }
            }
        }
        return historyList;
    }

    //注册
    @Override
    public void doGister() throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", "hh1551");
        jsonObject.put("Password", "hh1551");
        jsonObject.put("SureName", "唐华贵");
        jsonObject.put("PromoCode", "2581"+new Random().nextInt(9999));
        jsonObject.put("Mobile", "18877495804");
        jsonObject.put("QQ", "456787633");
        String res = HttpRequest.doReGisterPost(jsonObject);
        System.out.println(JSONObject.parse(res));
        System.out.println(jsonObject.getString("PromoCode"));
    }

    @Override
    public String getCookies(String UserName, String Password) throws ParseException {
        //根据账户和密码获取SessionId
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName",UserName);
        jsonObject.put("Password",Password);
        String res = HttpRequest.doLoginPost(jsonObject);
        System.out.println(res);
        JSONObject reswe = JSON.parseObject(res);
        JSONObject data =reswe.getJSONObject("Data");
        String SessionId = data.getString("SessionId");
        //获取SessionId之后去获取cookies，保持访问的会话
        JSONObject cookiesObject = new JSONObject();
        cookiesObject.put("ProviderId",33);
        cookiesObject.put("GameCode","qmft");
        cookiesObject.put("Language","zhCN");
        cookiesObject.put("Platform",1);
        String cookiesRes = HttpRequest.getCookies(cookiesObject,SessionId,UserName);
        System.out.println(cookiesRes);
        JSONObject reswe1 = JSON.parseObject(cookiesRes);
        String cookiesString =reswe1.getString("Data");
        String cookies = cookiesString.substring(30, 52);
        System.out.println(cookies);
        return cookies;
    }
}
