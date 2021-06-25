package com.example.blackoutsimulation.dao;

import com.example.blackoutsimulation.entity.BetHistory;
import com.example.blackoutsimulation.entity.BetTickets;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (BetTickets)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-22 12:17:10
 */
@Mapper
public interface BetTicketsDao {
    int betTicketsInsert(BetTickets betTickets);
    void betTicketsDeleteAll();
    List<Map<String,Object>> getBetTickets();


    int betHistoryInsert(BetHistory betHistory);
    int betHistoryDeleteAll();
    List<Map<String,Object>> getBetHistory();

    void betHistoryAnalyseInsert(BetHistory betHistory);

    void betSeasonNoInsert(String seasonNo);

    String getSeasonNo();

    List<Map<String, Object>> getSeasonNoList(String startTime,String endTime);

    List<Map<String, String>> getBetContent(String seasonNo);

    void betPlanInsert(String seasonNo, String toString);

    List<Map<String, Object>> getBetHistoryPage(String eds);

    void betAutoBetInsert(String seasonNo, String toString);

    List<String> getContentAuto(String startNo,String endNo);

    Map<String, Object> cookiesMap();

    void updateCookies(String Cookies,String CookiesFlag);
}