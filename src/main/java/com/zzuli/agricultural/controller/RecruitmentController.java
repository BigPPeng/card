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

    @RequestMapping(value = "/publishRecruitmentInfo")
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

    @RequestMapping(value = "/selectAllRecruitmentInfo")
    @ResponseBody
    public Response<List<RecruitmentInfo>> selectAllRecruitmentInfo() {
        return recruitmentService.selectAllRecruitmentInfo();
    }

    @RequestMapping(value = "/selectRecruitmentInfoByRecruitmentUserId")
    @ResponseBody
    public Response<List<RecruitmentInfo>> selectRecruitmentInfoByRecruitmentUserId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentInfoByRecruitmentUserId(idReq.getId());
    }


    @RequestMapping(value = "/applyRecruitment")
    @ResponseBody
    public Response<String> applyRecruitment(@RequestBody ApplyRecruitmentReq applyRecruitmentReq) {
        return recruitmentService.applyRecruitment(applyRecruitmentReq.getApplyUserId(), applyRecruitmentReq.getRecruitmentInfoId());
    }

    @RequestMapping(value = "/selectAllRecruitmentApplicationRecord")
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectAllRecruitmentApplicationRecord() {
        return recruitmentService.selectAllRecruitmentApplicationRecord();
    }

    @RequestMapping(value = "/delRecruitmentApplicationRecord")
    @ResponseBody
    public Response<String> delRecruitmentApplicationRecord(@RequestBody IdReq idReq) {
        return recruitmentService.delRecruitmentApplicationRecord(idReq.getId());
    }

    @RequestMapping(value = "/selectRecruitmentApplicationRecordByApplyUserId")
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByApplyUserId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentApplicationRecordByApplyUserId(idReq.getId());
    }

    @RequestMapping(value = "/selectRecruitmentApplicationRecordByRecruitmentInfoId")
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByRecruitmentInfoId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentApplicationRecordByRecruitmentInfoId(idReq.getId());
    }

    @RequestMapping(value = "/selectRecruitmentApplicationRecordByRecruitmentUserId")
    @ResponseBody
    public Response<List<RecruitmentApplicationRecord>> selectRecruitmentApplicationRecordByRecruitmentUserId(@RequestBody IdReq idReq) {
        return recruitmentService.selectRecruitmentApplicationRecordByRecruitmentUserId(idReq.getId());
    }


}
