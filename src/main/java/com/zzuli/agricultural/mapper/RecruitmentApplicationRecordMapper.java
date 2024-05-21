package com.zzuli.agricultural.mapper;


import com.zzuli.agricultural.model.RecruitmentApplicationRecord;

import java.util.List;
import java.util.Map;

public interface RecruitmentApplicationRecordMapper {
    int insertRecruitmentApplicationRecord(RecruitmentApplicationRecord recruitmentApplicationRecord);
    int updateRecruitmentApplicationRecord(RecruitmentApplicationRecord recruitmentApplicationRecord);
    RecruitmentApplicationRecord selectRecruitmentApplicationRecordById(Integer id);
    List<RecruitmentApplicationRecord> selectAllRecruitmentApplicationRecords();
    int deleteRecruitmentApplicationRecord(Integer id);
    List<RecruitmentApplicationRecord> selectByParams(Map<String,Object> params);
}
