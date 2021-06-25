package com.example.blackoutsimulation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (BetTickets)实体类
 *
 * @author makejava
 * @since 2020-11-22 12:17:09
 */
public class BetTickets implements Serializable {
    private static final long serialVersionUID = -98377563962913164L;
    /**
     * 业务id
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
     * 下注倍数
     */
    private String betodds;
    /**
     * 下注号码
     */
    private String betcontent;
    /**
     * 下注时间
     */
    private String createtime;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

}