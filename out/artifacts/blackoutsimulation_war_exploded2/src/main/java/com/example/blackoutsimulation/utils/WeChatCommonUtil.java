package com.example.blackoutsimulation.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WeChatCommonUtil {
    public static String sort(String pathUrl, String timestamp, String jsToken, String noncestr) throws NoSuchAlgorithmException {
        String sortString = "jsapi_ticket=" + jsToken + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + pathUrl;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            digest.update(sortString.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(shaHex);
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
