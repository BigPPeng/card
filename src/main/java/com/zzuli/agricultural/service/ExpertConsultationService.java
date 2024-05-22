package com.zzuli.agricultural.service;

import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.ExpertConsultationMapper;
import com.zzuli.agricultural.model.ExpertConsultation;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
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
public class ExpertConsultationService {
    @Autowired
    private ExpertConsultationMapper expertConsultationMapper;

    @Autowired
    private UserServiceV2 userServiceV2;

    /**
     * 发布咨询
     */
    public Response<String> publishConsultation(int publisherId, String consultationTitle,
                                                String consultationContent, String consultationKeywords) {
        User userById = userServiceV2.getUserById(publisherId);
        if (userById == null) {
            return new Response<>("咨询人注册信息查询失败，发布失败", 1);
        }
        if (StringUtils.isEmpty(consultationTitle) || StringUtils.isEmpty(consultationContent) || StringUtils.isEmpty(consultationKeywords)) {
            return new Response<>("咨询信息不完整，发布失败", 1);
        }
        ExpertConsultation build = ExpertConsultation.builder()
                .consultantId(publisherId)
                .consultantName(userById.getUsername())
                .consultationTime(DateToolUtil.getNow())
                .consultationKeywords(consultationKeywords)
                .consultationContent(consultationContent)
                .consultationTitle(consultationTitle).build();
        int i = expertConsultationMapper.insertExpertConsultation(build);
        if (i == 1) {
            return new Response<>("发布咨询成功", 0);
        }
        return new Response<>("发布咨询失败", 1);
    }

    /**
     * 删除咨询记录
     */
    public Response<String> delConsultation(int id) {
        int i = expertConsultationMapper.deleteExpertConsultation(id);
        if (i == 1) {
            return new Response<>("删除咨询成功", 0);
        }
        return new Response<>("删除咨询失败", 1);
    }

    /**
     * 根据id查询咨询
     */
    public Response<ExpertConsultation> getConsultationById(int id) {
        ExpertConsultation expertConsultation = expertConsultationMapper.selectExpertConsultationById(id);
        if (expertConsultation == null) {
            return new Response<>("查询失败，无咨询", 0, expertConsultation);
        }
        return new Response<>("查询成功", 0, expertConsultation);
    }


    /**
     * 查询所有咨询
     */
    public Response<String> responseConsultation(int responderId, int consultationId, String content) {
        User userById = userServiceV2.getUserById(responderId);
        if (userById == null || userById.getUserType() != UserTypeEnum.agricultural_technology_experts.type) {
            return new Response<>("答复人身份不正确", 1);
        }
        ExpertConsultation expertConsultation = expertConsultationMapper.selectExpertConsultationById(consultationId);
        if (expertConsultation == null || !StringUtils.isEmpty(expertConsultation.getResponseContent()) || expertConsultation.getResponderId() != null) {
            return new Response<>("已经答复，无需重复答复", 1);
        }
        if (StringUtils.isEmpty(content)) {
            return new Response<>("答复内容为空", 1);
        }
        ExpertConsultation build = ExpertConsultation.builder()
                .id(consultationId)
                .consultantId(expertConsultation.getConsultantId())
                .consultantName(expertConsultation.getConsultantName())
                .consultationTime(expertConsultation.getConsultationTime())
                .consultationKeywords(expertConsultation.getConsultationKeywords())
                .consultationContent(expertConsultation.getConsultationContent())
                .consultationTitle(expertConsultation.getConsultationTitle())
                .responderId(responderId)
                .responseTime(DateToolUtil.getNow())
                .responderName(userById.getUsername())
                .responseContent(content)
                .build();
        int i = expertConsultationMapper.updateExpertConsultation(build);
        if (i == 1) {
            return new Response<>("答复咨询成功", 0);
        }
        return new Response<>("答复咨询失败", 1);
    }

    /**
     * 查询所有咨询
     */
    public Response<List<ExpertConsultation>> getAllConsultation() {
        List<ExpertConsultation> expertConsultations = expertConsultationMapper.selectAllExpertConsultations();
        if (CollectionUtils.isEmpty(expertConsultations)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, expertConsultations);
    }

    /**
     * 根据发布人id查询所有咨询
     */
    public Response<List<ExpertConsultation>> getConsultationByPublisherId(int publisherId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("consultantId", publisherId);
        List<ExpertConsultation> expertConsultations = expertConsultationMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(expertConsultations)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, expertConsultations);
    }

    /**
     * 根据回复人ID查询所有咨询
     */
    public Response<List<ExpertConsultation>> getConsultationByResponderId(int responderId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("responderId", responderId);
        List<ExpertConsultation> expertConsultations = expertConsultationMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(expertConsultations)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, expertConsultations);
    }
}
