package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.AgriculturalNews;

import java.util.List;
import java.util.Map;

public interface AgriculturalNewsMapper {
    void insertAgriculturalNews(AgriculturalNews user);
    void deleteAgriculturalNews(Integer id);
    void updateAgriculturalNews(AgriculturalNews user);
    AgriculturalNews selectAgriculturalNewsById(Integer id);
    List<AgriculturalNews> selectAllAgriculturalNews();
    List<AgriculturalNews> selectByParams(Map<String,Object> params);
}
