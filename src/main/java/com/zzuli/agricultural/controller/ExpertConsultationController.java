package com.zzuli.agricultural.controller;

import com.card.common.Response;
import com.zzuli.agricultural.model.ExpertConsultation;
import com.zzuli.agricultural.model.request.IdReq;
import com.zzuli.agricultural.model.request.PublishConsultationReq;
import com.zzuli.agricultural.model.request.ResponseConsultationReq;
import com.zzuli.agricultural.model.request.StringReq;
import com.zzuli.agricultural.service.ExpertConsultationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/consultation")
@CrossOrigin
public class ExpertConsultationController {

    @Autowired
    private ExpertConsultationService consultationService;

    @RequestMapping(value = "/publishConsultation")
    @ResponseBody
    public Response<String> publishConsultation(@RequestBody PublishConsultationReq req) {
        return consultationService.publishConsultation(req.getPublisherId(), req.getConsultationTitle(), req.getConsultationContent(),
                req.getConsultationKeywords());
    }

    @RequestMapping(value = "/delConsultation")
    @ResponseBody
    public Response<String> delConsultation(@RequestBody IdReq idReq) {
        return consultationService.delConsultation(idReq.getId());
    }

    @RequestMapping(value = "/getConsultationById")
    @ResponseBody
    public Response<ExpertConsultation> getConsultationById(@RequestBody IdReq idReq) {
        return consultationService.getConsultationById(idReq.getId());
    }

    @RequestMapping(value = "/getConsultationByResponderName")
    @ResponseBody
    public Response<List<ExpertConsultation>> getConsultationById(@RequestBody  StringReq stringReq) {
        return consultationService.getConsultationByResponder(stringReq.getId(), stringReq.getResponderName());
    }

    @RequestMapping(value = "/responseConsultation")
    @ResponseBody
    public Response<String> responseConsultation(@RequestBody ResponseConsultationReq req) {
        return consultationService.responseConsultation(req.getResponderId(), req.getConsultationId(), req.getContent());
    }


    @RequestMapping(value = "/getAllConsultation")
    @ResponseBody
    public Response<List<ExpertConsultation>> getAllConsultation() {
        return consultationService.getAllConsultation();
    }

    @RequestMapping(value = "/getConsultationByPublisherId")
    @ResponseBody
    public Response<List<ExpertConsultation>> getConsultationByPublisherId(@RequestBody IdReq idReq) {
        return consultationService.getConsultationByPublisherId(idReq.getId());
    }

    @RequestMapping(value = "/getConsultationByResponderId")
    @ResponseBody
    public Response<List<ExpertConsultation>> getConsultationByResponderId(@RequestBody IdReq idReq) {
        return consultationService.getConsultationByResponderId(idReq.getId());
    }


}
