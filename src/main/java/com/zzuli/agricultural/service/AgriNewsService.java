package com.zzuli.agricultural.service;

import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.AgriculturalNewsMapper;
import com.zzuli.agricultural.model.AgriculturalNews;
import com.zzuli.agricultural.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AgriNewsService {
    @Autowired
    private AgriculturalNewsMapper agriculturalNewsMapper;

    @Autowired
    private UserServiceV2 userServiceV2;

    /**
     * 发布资讯
     */
    public Response<String> publishNews(int publisherId, String title,String keywords, String content, String source) {
        User userById = userServiceV2.getUserById(publisherId);
        if (userById == null) {
            return new Response<>("发布人注册信息查询失败，发布失败", 1);
        }
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(keywords) || StringUtils.isEmpty(content) || StringUtils.isEmpty(source)) {
            return new Response<>("咨询信息不完整，发布失败", 1);
        }
        AgriculturalNews build = AgriculturalNews.builder().publisher(userById.getUsername()).publisherId(publisherId).source(source)
                .publishKeywords(keywords).publishTime(DateToolUtil.getNow()).content(content).title(title).build();
        agriculturalNewsMapper.insertAgriculturalNews(build);
        return new Response<>("发布咨询成功", 0);
    }

    /**
     * 删除咨询记录
     */
    public Response<String> delNews(int id) {
        agriculturalNewsMapper.deleteAgriculturalNews(id);
        return new Response<>("删除成功", 0);
    }

    /**
     * 根据id查询咨询
     */
    public Response<AgriculturalNews> getNewsById(int id) {
        AgriculturalNews agriculturalNews = agriculturalNewsMapper.selectAgriculturalNewsById(id);
        if (agriculturalNews == null) {
            return new Response<>("查询失败，无资讯", 0, agriculturalNews);
        }
        return new Response<>("查询成功", 0, agriculturalNews);
    }

    /**
     * 查询所有咨询
     */
    public Response<List<AgriculturalNews>> getAllNews() {
        List<AgriculturalNews> agriculturalNews = agriculturalNewsMapper.selectAllAgriculturalNews();
        if (CollectionUtils.isEmpty(agriculturalNews)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, agriculturalNews);
    }

    /**
     * 根据发布人id查询所有咨询
     */
    public Response<List<AgriculturalNews>> getNewsByPublisherId(int publisherId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("publisherId", publisherId);
        List<AgriculturalNews> agriculturalNews = agriculturalNewsMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(agriculturalNews)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, agriculturalNews);
    }
}
