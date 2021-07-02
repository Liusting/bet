package com.example.blackoutsimulation.utils;

public class MapUtils {
    /**
     * 地球半径 - 单位：米
     */
    public static final Double EARTH_R_METERS = 6370996.81;

    /**
     * 根据经纬度来计算距离
     * @param r -- 半径
     * @param lng1 -- 点1经度
     * @param lat1 -- 点1纬度
     * @param lng2 -- 点2经度
     * @param lat2 -- 点2纬度
     * @return
     */
    public static Double getLatLngDistance(
                                           double lng1,double lat1,
                                           double lng2,double lat2){
        Double result = EARTH_R_METERS * Math.acos(Math.cos(lat1*Math.PI/180) *Math.cos(lat2*Math.PI/180) *Math.cos( (lng1-lng2)*Math.PI/180 )
                + Math.sin(lat1*Math.PI/180) * Math.sin(lat2*Math.PI/180));
        return result;
    }
}
