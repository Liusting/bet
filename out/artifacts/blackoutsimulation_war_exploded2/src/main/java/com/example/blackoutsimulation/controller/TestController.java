package com.example.blackoutsimulation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.blackoutsimulation.BlackoutsimulationApplication;
import com.example.blackoutsimulation.dao.ArticleMapper;
import com.example.blackoutsimulation.entity.ResquestInfo;
import com.example.blackoutsimulation.utils.HttpRequest;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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
       ResquestInfo map =  articleMapper.getArticle(id);
       return map;
    }

}
