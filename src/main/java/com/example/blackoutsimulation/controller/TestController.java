package com.example.blackoutsimulation.controller;

import com.example.blackoutsimulation.dao.ArticleMapper;
import com.example.blackoutsimulation.entity.ResquestInfo;
import com.example.blackoutsimulation.utils.IdUtil;
import com.example.blackoutsimulation.utils.MapUtils;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@ResponseBody
@RequestMapping("/getMsg")
public class TestController {

    @Autowired
    ArticleMapper articleMapper;

    @PostMapping("/getArticle")
    @ResponseBody
    @CrossOrigin
    public void getHtml(@RequestBody ResquestInfo resquestInfo) {
        resquestInfo.setId(UUID.randomUUID().toString().replace("-",""));
        articleMapper.insertArticle(resquestInfo);
        System.out.println(resquestInfo);
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping(value = "/getArticleMsg/{id}")
    public ResquestInfo getArticle(@PathVariable String id) {
        String id1 = "PS"+ IdUtil.getId();
        System.out.println(id1);
       ResquestInfo map =  articleMapper.getArticle(id);
       return map;
    }



    public static void main(String[] args) throws WriterException, IOException {

        double res = MapUtils.getLatLngDistance(113.163047,23.067995,113.175804,23.055902);
            System.out.println(res);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("区域：","C区");
//        jsonObject.put("区域编号：","BG6789876");
//        jsonObject.put("存放物品：","豆角、干辣椒和香叶");
//        String text = jsonObject.toJSONString();
//        int width = 100;
//        int height = 100;
//        String format ="png";
//        Hashtable hints = new Hashtable();
//        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,width ,height , hints);
//        File outfile = new File("new.png");
//        MatrixToImageWriter.writeToFile(bitMatrix,format,outfile);
    }

}
