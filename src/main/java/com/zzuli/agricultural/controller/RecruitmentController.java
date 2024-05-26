package com.zzuli.agricultural.controller;

import com.card.common.Response;
import com.zzuli.agricultural.model.*;
import com.zzuli.agricultural.model.request.*;
import com.zzuli.agricultural.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/recruitment")
@CrossOrigin
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @RequestMapping(value = "/publishRecruitmentInfo") //发布雇用信息
    @ResponseBody
    public Response<String> publishRecruitmentInfo(@RequestBody PublishRecruitmentReq publishRecruitmentReq) {
        return recruitmentService.publishRecruitmentInfo(publishRecruitmentReq.getRecruitmentUserId(),
                publishRecruitmentReq.getTitle(), publishRecruitmentReq.getJobDescription(),
                publishRecruitmentReq.getRecruitmentTime(), publishRecruitmentReq.getRecruitmentQuantity());
    }

    @RequestMapping(value = "/delRecruitmentInfo")
    @ResponseBody
    public Response<String> delRecruitmentInfo(@RequestBody IdReq idReq) {
        return recruitmentService.delRecruitmentInfo(idReq.getId());
    }

    @RequestMapping(value = "/selectAllRecruitmentInfo")  //选择所有雇用信息
    @ResponseBody
    public Response<List<RecruitmentInfo>> selectAllRecruitmentInfo() {
        return recruitmentService.selectAllRecruitmentInfo();
    }

    @RequestMapping(value = "/selectRecruitmentInfoByRecruitmentUserId") //选择“雇用信息”“按招聘用户ID”
    @ResponseBody
    public Response<List<RecruitmentInfo>> selectRecruitmentInfoByRecruitmentUserId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentInfoByRecruitmentUserId(idReq.getId());
    }


    @RequestMapping(value = "/applyRecruitment") //申请招聘
    @ResponseBody
    public Response<String> applyRecruitment(@RequestBody ApplyRecruitmentReq applyRecruitmentReq) {
        return recruitmentService.applyRecruitment(applyRecruitmentReq.getApplyUserId(), applyRecruitmentReq.getRecruitmentInfoId());
    }

    @RequestMapping(value = "/selectAllRecruitmentApplicationRecord")//招聘记录
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectAllRecruitmentApplicationRecord() {
        return recruitmentService.selectAllRecruitmentApplicationRecord();
    }

    @RequestMapping(value = "/delRecruitmentApplicationRecord")
    @ResponseBody
    public Response<String> delRecruitmentApplicationRecord(@RequestBody IdReq idReq) {
        return recruitmentService.delRecruitmentApplicationRecord(idReq.getId());
    }

    @RequestMapping(value = "/selectRecruitmentApplicationRecordByApplyUserId")  //按申请用户Id选择招聘申请记录
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByApplyUserId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentApplicationRecordByApplyUserId(idReq.getId());
    }

    @RequestMapping(value = "/selectRecruitmentApplicationRecordByRecruitmentInfoId")  //选择招聘申请记录按招聘信息ID
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByRecruitmentInfoId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentApplicationRecordByRecruitmentInfoId(idReq.getId());
    }

    @RequestMapping(value = "/selectRecruitmentApplicationRecordByRecruitmentUserId") //按招聘用户Id选择招聘申请记录
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByRecruitmentUserId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentApplicationRecordByRecruitmentUserId(idReq.getId());
    }


}
