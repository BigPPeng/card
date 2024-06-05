package com.zzuli.agricultural.service;

import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.RecruitmentApplicationRecordMapper;
import com.zzuli.agricultural.mapper.RecruitmentInfoMapper;
import com.zzuli.agricultural.mapper.UserMapper2;
import com.zzuli.agricultural.model.RecruitmentApplicationRecord;
import com.zzuli.agricultural.model.RecruitmentInfo;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 招工模块服务
 */
@Slf4j
@Service
public class RecruitmentService {

    // 招工信息
    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    // 招工报名信息
    @Autowired
    private RecruitmentApplicationRecordMapper recruitmentApplicationRecordMapper;

    // 用户信息
    @Autowired
    private UserMapper2 userMapper2;

    /**
     * 发布招工信息
     *
     * @param recruitmentUserId   发布人
     * @param title               标题
     * @param jobDescription      描述
     * @param recruitmentTime     工作开始时间
     * @param recruitmentQuantity 需求人数
     * @return
     */
    public Response<String> publishRecruitmentInfo(int recruitmentUserId, String title, String jobDescription,
                                                   int recruitmentTime, int recruitmentQuantity) {
        User user = userMapper2.selectUser(recruitmentUserId);
        if (user == null || user.getUserType() != UserTypeEnum.administrators.type) {
            new Response<>("招工信息需要注册的管理员发布", 1);
        }
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(jobDescription)) {
            new Response<>("招工信息不完整", 1);
        }
        RecruitmentInfo build = RecruitmentInfo.builder()
                .recruitmentTime(recruitmentTime < 0 ? DateToolUtil.getNow() : recruitmentTime)
                .recruitmentQuantity(recruitmentQuantity < 0 ? 100 : recruitmentQuantity)
                .recruiterUserId(recruitmentUserId)
                .jobDescription(jobDescription)
                .recruitmentTitle(title)
                .publishTime(DateToolUtil.getNow())
                .build();
        int i = recruitmentInfoMapper.insertRecruitmentInfo(build);
        if (i == 1) {
            return new Response<>("招工信息发布成功", 0);
        }
        return new Response<>("招工信息发布失败，请重试", 1);
    }

    public Response<String> updateRecruitmentInfo(int id, String title, String jobDescription, int recruitmentTime, int recruitmentQuantity) {
        RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.selectRecruitmentInfoById(id);
        if (recruitmentInfo == null) {
            return new Response<>("更新的信息不存在", 1);
        }
        if (!StringUtils.isEmpty(title)) {
            recruitmentInfo.setRecruitmentTitle(title);
        }
        if (!StringUtils.isEmpty(jobDescription)) {
            recruitmentInfo.setJobDescription(jobDescription);
        }
        if (recruitmentTime > 0) {
            recruitmentInfo.setRecruitmentTime(recruitmentTime);
        }
        if (recruitmentQuantity > 0) {
            recruitmentInfo.setRecruitmentQuantity(recruitmentQuantity);
        }
        int i = recruitmentInfoMapper.updateRecruitmentInfo(recruitmentInfo);
        if (i == 1) {
            return new Response<>("更新成功", 0);
        }
        return new Response<>("更新失败", 1);
    }


    /**
     * 删除招工信息
     *
     * @param recruitmentInfoId
     * @return
     */
    public Response<String> delRecruitmentInfo(int recruitmentInfoId) {
        int i = recruitmentInfoMapper.deleteRecruitmentInfo(recruitmentInfoId);
        if (i == 1) {
            return new Response<>("招工信息删除成功", 0);
        }
        return new Response<>("招工信息删除失败，请重试", 1);
    }

    /**
     * 查询所有的招工信息
     *
     * @return
     */
    public Response<List<RecruitmentInfo>> selectAllRecruitmentInfo() {
        List<RecruitmentInfo> recruitmentInfos = recruitmentInfoMapper.selectAllRecruitmentInfos();
        return new Response<>("查询成功", 0, Optional.ofNullable(recruitmentInfos).orElse(Collections.emptyList()));
    }

    /**
     * 根据发布人id查询其发布的所有招工信息
     *
     * @param recruitmentUserId
     * @return
     */
    public Response<List<RecruitmentInfo>> selectRecruitmentInfoByRecruitmentUserId(int recruitmentUserId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("recruiterUserId", recruitmentUserId);
        List<RecruitmentInfo> recruitmentInfos = recruitmentInfoMapper.selectByParams(params);
        return new Response<>("查询成功", 0, Optional.ofNullable(recruitmentInfos).orElse(Collections.emptyList()));
    }

    /**
     * 招工报名
     *
     * @param applyUserId       报名人ID
     * @param recruitmentInfoId 招工信息ID
     * @return
     */
    public Response<String> applyRecruitment(int applyUserId, int recruitmentInfoId) {
        User user = userMapper2.selectUser(applyUserId);
        if (user == null || user.getUserType() != UserTypeEnum.farmers.type) {
            return new Response<>("报名人未注册或者不是农民", 1);
        }
        RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.selectRecruitmentInfoById(recruitmentInfoId);
        if (recruitmentInfo == null) {
            return new Response<>("招工信息不存在", 1);
        }

        RecruitmentApplicationRecord build = RecruitmentApplicationRecord.builder()
                .applicantId(applyUserId)
                .applicantName(user.getUsername())
                .applicationTime(DateToolUtil.getNow())
                .recruitmentInfoId(recruitmentInfoId).build();
        int i = recruitmentApplicationRecordMapper.insertRecruitmentApplicationRecord(build);
        if (i == 1) {
            return new Response<>("招工报名成功", 0);
        }
        return new Response<>("招工报名失败，请重试", 1);
    }

    /**
     * 查询所有的招工报名信息
     */
    public Response<List<RecruitmentApplicationRecord>> selectAllRecruitmentApplicationRecord() {
        List<RecruitmentApplicationRecord> recruitmentApplicationRecords = recruitmentApplicationRecordMapper.selectAllRecruitmentApplicationRecords();
        return new Response<>("查询成功", 0, Optional.ofNullable(recruitmentApplicationRecords).orElse(Collections.emptyList()));
    }

    /**
     * 删除招工报名信息
     */
    public Response<String> delRecruitmentApplicationRecord(int id) {
        int i = recruitmentApplicationRecordMapper.deleteRecruitmentApplicationRecord(id);
        if (i == 1) {
            return new Response<>("招工报名信息删除成功", 0);
        }
        return new Response<>("招工报名信息删除失败，请重试", 1);
    }

    /**
     * 根据申请人id查询招工报名信息
     *
     * @param applyUserId
     * @return
     */
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByApplyUserId(int applyUserId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("applicantId", applyUserId);
        List<RecruitmentApplicationRecord> recruitmentApplicationRecords = recruitmentApplicationRecordMapper.selectByParams(params);
        return new Response<>("查询成功", 0, Optional.ofNullable(recruitmentApplicationRecords).orElse(Collections.emptyList()));
    }

    /**
     * 根据招工信息ID 查询招工报名信息
     */
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByRecruitmentInfoId(int recruitmentInfoId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("recruitmentInfoId", recruitmentInfoId);
        List<RecruitmentApplicationRecord> recruitmentApplicationRecords = recruitmentApplicationRecordMapper.selectByParams(params);
        return new Response<>("查询成功", 0, Optional.ofNullable(recruitmentApplicationRecords).orElse(Collections.emptyList()));
    }

    /**
     * 根据发布人查询其所有发布的招工信息对应的招工报名信息
     */
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByRecruitmentUserId(int recruitmentUserId) {
        Response<List<RecruitmentInfo>> allRecruitmentInfo = this.selectRecruitmentInfoByRecruitmentUserId(recruitmentUserId);
        if (allRecruitmentInfo.getStatus() != 0 || CollectionUtils.isEmpty(allRecruitmentInfo.getData())) {
            return new Response<>("无招工信息发布，没有报名信息", 0);
        }
        Set<Integer> allRecruitmentInfoId = allRecruitmentInfo.getData().stream().map(RecruitmentInfo::getId).collect(Collectors.toSet());

        List<RecruitmentApplicationRecord> all = Lists.newArrayList();
        for (Integer i : allRecruitmentInfoId) {
            Response<List<RecruitmentApplicationRecord>> listResponse = selectRecruitmentApplicationRecordByRecruitmentInfoId(i);
            if (listResponse.getStatus() == 0 && !CollectionUtils.isEmpty(listResponse.getData())) {
                all.addAll(listResponse.getData());
            }
        }
        return new Response<>("查询成功", 0, all);
    }
}
