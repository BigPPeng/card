package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.RecruitmentInfo;

import java.util.List;
import java.util.Map;

public interface RecruitmentInfoMapper {
    int insertRecruitmentInfo(RecruitmentInfo recruitmentInfo);
    int updateRecruitmentInfo(RecruitmentInfo recruitmentInfo);
    RecruitmentInfo selectRecruitmentInfoById(Integer id);
    List<RecruitmentInfo> selectAllRecruitmentInfos();
    int deleteRecruitmentInfo(Integer id);
    List<RecruitmentInfo> selectByParams(Map<String,Object> params);
}
