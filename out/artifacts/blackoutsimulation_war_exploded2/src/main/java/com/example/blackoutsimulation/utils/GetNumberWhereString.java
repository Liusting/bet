package com.example.blackoutsimulation.utils;

public class GetNumberWhereString {
    public static String getNumberFromString(String str){
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        return  str2;
    }
}
