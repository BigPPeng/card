package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.ExpertConsultation;

import java.util.List;
import java.util.Map;

public interface ExpertConsultationMapper {
    int insertExpertConsultation(ExpertConsultation expertConsultation);
    int updateExpertConsultation(ExpertConsultation expertConsultation);
    ExpertConsultation selectExpertConsultationById(Integer id);
    List<ExpertConsultation> selectAllExpertConsultations();
    int deleteExpertConsultation(Integer id);
    List<ExpertConsultation> selectByParams(Map<String,Object> params);
}
