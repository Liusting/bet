package com.example.blackoutsimulation.entity;

import java.io.Serializable;

/**
 * (BetHistory)实体类
 *
 * @author makejava
 * @since 2020-11-22 22:05:25
 */
public class BetHistory implements Serializable {
    private static final long serialVersionUID = -91967644945770669L;
    /**
     * id
     */
    private String id;
    /**
     * 下注时间
     */
    private String bettime;
    /**
     * 下注金额
     */
    private String betamount;
    /**
     * 号码倍数
     */
    private String betodds;
    /**
     * 下注号码
     */
    private String betcontent;
    /**
     * 输赢金额
     */
    private String winlose;
    /**
     * 赢或者输
     */
    private String wincount;
    /**
     * 更新时间
     */
    private String createtime;
    /**
     * 开奖号码
     */
    private String number;
    /**
     * 今日盈亏金额
     */
    private String totalwinLose;
    /**
     * 下注时间
     */
    private String seasonno;


    public String getSeasonno() {
        return seasonno;
    }

    public void setSeasonno(String seasonno) {
        this.seasonno = seasonno;
    }


    public String getTotalwinLose() {
        return totalwinLose;
    }

    public void setTotalwinLose(String totalwinLose) {
        this.totalwinLose = totalwinLose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBettime() {
        return bettime;
    }

    public void setBettime(String bettime) {
        this.bettime = bettime;
    }

    public String getBetamount() {
        return betamount;
    }

    public void setBetamount(String betamount) {
        this.betamount = betamount;
    }

    public String getBetodds() {
        return betodds;
    }

    public void setBetodds(String betodds) {
        this.betodds = betodds;
    }

    public String getBetcontent() {
        return betcontent;
    }

    public void setBetcontent(String betcontent) {
        this.betcontent = betcontent;
    }

    public String getWinlose() {
        return winlose;
    }

    public void setWinlose(String winlose) {
        this.winlose = winlose;
    }

    public String getWincount() {
        return wincount;
    }

    public void setWincount(String wincount) {
        this.wincount = wincount;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}