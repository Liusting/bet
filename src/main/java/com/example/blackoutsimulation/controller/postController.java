//package com.example.blackoutsimulation.controller;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.example.blackoutsimulation.dao.BetTicketsDao;
//import com.example.blackoutsimulation.entity.BetHistory;
//import com.example.blackoutsimulation.utils.HttpRequest;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//import java.io.IOException;
//
//import java.text.ParseException;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//
//@RequestMapping("/checkBet")
//@RestController
//public class postController {
//    @Autowired
//    private BetTicketsDao betTicketsDao;
//
//
//    @Value("${common.cookies}")
//    private String cookies;
//
//    @Value("${common.bethistory}")
//    private String bethistory;
//
//    /**
//     * 获取最新一期下注号数记录
//     *
//     * @return
//     */
//    @GetMapping("/getBetTickets")
//    @ResponseBody
//    @CrossOrigin
//    public List<Map<String, Object>> getBetTickets() {
//        List<Map<String, Object>> list = betTicketsDao.getBetTickets();
//        return list;
//
//    }
//
//
//    /**
//     * 获取账户开奖记录
//     *
//     * @return
//     */
//    @GetMapping("/getBetHistory")
//    @ResponseBody
//    @CrossOrigin
//    @ApiOperation(value = "/getBetHistory" )
//    public List<Map<String, Object>> getBetHistory() {
//        List<Map<String, Object>> list = betTicketsDao.getBetHistory();
//        return list;
//    }
//
//    /**
//     * 查询获取的数据分析，进行分页
//     *
//     * @param req
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/getBetHistoryPageInfo")
//    @ResponseBody
//    @CrossOrigin
//    public PageInfo<Map<String, Object>> getBetHistoryPageInfo(HttpServletRequest req) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader reader = req.getReader()) {
//            char[] buff = new char[1024];
//            int len;
//            while ((len = reader.read(buff)) != -1) {
//                sb.append(buff, 0, len);
//            }
//        }
//        JSONObject jsonObject = JSON.parseObject(sb.toString());
//        Integer pageNum = Integer.parseInt(jsonObject.getString("pageNum"));
//        Integer pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
//        List<Map<String, Object>> list = betTicketsDao.getBetHistoryPage(jsonObject.getString("sortOrder"));
//        PageHelper.startPage(pageNum, pageSize);
//
//        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
//        return pageInfo;
//    }
//
//    /**
//     * 查看某个日期数据，进行分页
//     *
//     * @param req
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/getDatePageInfo")
//    @ResponseBody
//    @CrossOrigin
//    public PageInfo<Map<String, Object>> getDatePageInfo(HttpServletRequest req) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader reader = req.getReader()) {
//            char[] buff = new char[1024];
//            int len;
//            while ((len = reader.read(buff)) != -1) {
//                sb.append(buff, 0, len);
//            }
//        }
//        JSONObject jsonObject = JSON.parseObject(sb.toString());
//        Integer pageNum = Integer.parseInt(jsonObject.getString("pageNum"));
//        Integer pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
//        PageHelper.startPage(pageNum, pageSize);
//        List<Map<String, Object>> list = betTicketsDao.getSeasonNoList(jsonObject.getString("startTime"), jsonObject.getString("endTime"));
//        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
//        return pageInfo;
//    }
//
//
//    /**
//     * 手动获取过去日期的数据，方便数据分析
//     *
//     * @return
//     */
//    @GetMapping("/getDateBetHistory")
//    @ResponseBody
//    @CrossOrigin
//    public String getDateBetHistory(String date) throws ParseException {
//        ZonedDateTime zdt = ZonedDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        for (int i = 1; i <= 30; i++) {
//            String reshistory = HttpRequest.domorePost(bethistory, cookies, String.valueOf(i), date);
//            if (!StringUtils.isEmpty(reshistory)) {
//                JSONObject resObject = JSON.parseObject(reshistory);
//                String TotalWinLose = resObject.getString("TotalWinLose");
//                JSONArray historyList = resObject.getJSONArray("Tickets");
//                System.out.println("开始获取历史记录");
//                if (historyList.size() > 0) {
//                    betTicketsDao.betHistoryDeleteAll();
//                    for (int j = 0; j < historyList.size(); j++) {
//                        BetHistory betHistory = new BetHistory();
//                        String number = historyList.getJSONObject(j).getString("draw");
//                        String substring = number.substring(0, number.length() - 1);
//                        String[] result = substring.split(",");
//                        int re = Integer.parseInt(result[0]);
//                        int de = Integer.parseInt(result[1]);
//                        int rightNumber = re + de;
//
//                        String str = historyList.getJSONObject(j).getString("BetContent");
//                        str = str.trim();
//                        String str2 = "";
//                        if (str != null && !"".equals(str)) {
//                            for (int h = 0; h < str.length(); h++) {
//                                if (str.charAt(h) >= 48 && str.charAt(h) <= 57) {
//                                    str2 += str.charAt(h);
//                                }
//                            }
//                        }
//                        betHistory.setId(historyList.getJSONObject(j).getString("TransId"));
//                        betHistory.setBettime(historyList.getJSONObject(j).getString("BetTime"));
//                        betHistory.setBetamount(historyList.getJSONObject(j).getString("BetAmount"));
//                        betHistory.setBetodds(historyList.getJSONObject(j).getString("BetOdds"));
//                        betHistory.setBetcontent(str2);
//                        betHistory.setWinlose(historyList.getJSONObject(j).getString("WinLose"));
//                        betHistory.setWincount(historyList.getJSONObject(j).getString("WinAmount"));
//                        betHistory.setSeasonno(historyList.getJSONObject(j).getString("SeasonNo"));
//                        betHistory.setCreatetime(formatter.format(zdt));
//                        betHistory.setNumber(String.valueOf(rightNumber));
//                        betHistory.setTotalwinLose(TotalWinLose);
//                        betTicketsDao.betHistoryAnalyseInsert(betHistory);
//                    }
//                }
//            }
//        }
//        return "成功！";
//    }
//
//
//    /**
//     * 将手动获取的日期数据生成下注计划
//     *
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    @GetMapping("/createPlan")
//    @ResponseBody
//    @CrossOrigin
//    public Map<String, Object> checkItem(String startTime, String endTime) {
//        //先根据时间段将期号找出来
//        List<Map<String, Object>> seasonList = betTicketsDao.getSeasonNoList(startTime, endTime);
//        for (int i = 0; i < seasonList.size(); i++) {
//            List<Map<String, String>> betContent = betTicketsDao.getBetContent(seasonList.get(i).get("SeasonNo").toString());
//            StringBuffer sb = new StringBuffer();
//            for (int j = 0; j < betContent.size(); j++) {
//                if (j == betContent.size() - 1) {
//                    sb.append("[" + betContent.get(j).get("betContent") + "]");
//                } else {
//                    sb.append("[" + betContent.get(j).get("betContent") + "],");
//                }
//            }
//            betTicketsDao.betPlanInsert(seasonList.get(i).get("SeasonNo").toString(), sb.toString());
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 200);
//        map.put("message", "成功");
//        return map;
//    }
//
//
//    /**
//     * 将手动获取日期数据进行分析，方便后面手动下注方案
//     *
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    @GetMapping("/createBetAutoPlan")
//    @ResponseBody
//    @CrossOrigin
//    public Map<String, Object> betAuto(String startTime, String endTime) {
//        //先将期号找出来
//        List<Map<String, Object>> seasonList = betTicketsDao.getSeasonNoList(startTime, endTime);
//        for (int i = 0; i < seasonList.size(); i++) {
//            List<Map<String, String>> betContent = betTicketsDao.getBetContent(seasonList.get(i).get("SeasonNo").toString());
//            StringBuffer sb = new StringBuffer();
//            StringBuffer sbzui = new StringBuffer();
//            int len = betContent.size();
//            for (int j = 0; j < len; j++) {
//                if (j == len - 1) {
//                    sb.append("[" + 11 + "," + "[" + betContent.get(j).get("betContent") + "]" + "," + "[" + betContent.get(j).get("betOdds") + "]" + "," + betContent.get(j).get("betAmount") + "]");
//                } else {
//                    sb.append("[" + 11 + "," + "[" + betContent.get(j).get("betContent") + "]" + "," + "[" + betContent.get(j).get("betOdds") + "]" + "," + betContent.get(j).get("betAmount") + "],");
//                }
//            }
//            if (len != 1) {
//                sbzui.append("[" + sb.toString() + "]");
//            } else {
//                sbzui.append(sb.toString());
//            }
//            betTicketsDao.betAutoBetInsert(seasonList.get(i).get("SeasonNo").toString(), sbzui.toString());
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 200);
//        map.put("message", "成功");
//        return map;
//    }
//
//    /**
//     * 退出登录
//     *
//     * @return
//     */
//    @GetMapping("/logout")
//    @ResponseBody
//    @CrossOrigin
//    public Integer loginout() {
//
//        return 200;
//    }
//}
