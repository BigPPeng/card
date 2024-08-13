package com.zzuli.agricultural.controller;

import com.zzuli.agricultural.common.Response;
import com.zzuli.agricultural.model.AgriculturalNews;
import com.zzuli.agricultural.model.request.IdReq;
import com.zzuli.agricultural.model.request.PublishNewsReq;
import com.zzuli.agricultural.service.AgriNewsService;
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
@RequestMapping(value = "/argiNews")
@CrossOrigin
public class AgriNewsController {

    @Autowired
    private AgriNewsService agriNewsService;

    @RequestMapping(value = "/publishNews")
    @ResponseBody
    public Response<String> publishNews(@RequestBody PublishNewsReq req) {
        return agriNewsService.publishNews(req.getPublisherId(), req.getTitle(), req.getKeywords(), req.getContent(), req.getSource());
    }

    @RequestMapping(value = "/delNews")
    @ResponseBody
    public Response<String> delNews(@RequestBody IdReq idReq) {
        return agriNewsService.delNews(idReq.getId());
    }


    @RequestMapping(value = "/getNewsById")
    @ResponseBody
    public Response<AgriculturalNews> getNewsById(@RequestBody IdReq idReq) {
        return agriNewsService.getNewsById(idReq.getId());
    }

    @RequestMapping(value = "/getAllNews")
    @ResponseBody
    public Response<List<AgriculturalNews>> getAllNews() {
        return agriNewsService.getAllNews();
    }

    @RequestMapping(value = "/getNewsByPublisherId")
    @ResponseBody
    public Response<List<AgriculturalNews>> getConsultationByPublisherId(@RequestBody IdReq idReq) {
        return agriNewsService.getNewsByPublisherId(idReq.getId());
    }

}
