package com.example.blackoutsimulation.dao;

import com.example.blackoutsimulation.entity.ResquestInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    void insertArticle(ResquestInfo resquestInfo);

    ResquestInfo getArticle(String id);
}
