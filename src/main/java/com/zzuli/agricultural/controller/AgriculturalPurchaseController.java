package com.zzuli.agricultural.controller;

import com.zzuli.agricultural.common.Response;
import com.zzuli.agricultural.model.AgriculturalPurchaseInfo;
import com.zzuli.agricultural.model.AgriculturalSaleInfo;
import com.zzuli.agricultural.model.request.IdReq;
import com.zzuli.agricultural.model.request.NameReq;
import com.zzuli.agricultural.model.request.PurchaseReq;
import com.zzuli.agricultural.model.request.SaleReq;
import com.zzuli.agricultural.service.AgriculturalPurchaseInfoService;
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
@RequestMapping(value = "/purchase")
@CrossOrigin
public class AgriculturalPurchaseController {

    @Autowired
    private AgriculturalPurchaseInfoService agriculturalPurchaseInfoService;


    // 发布收购信息
    @RequestMapping(value = "/publishPurchase")
    @ResponseBody
    public Response<String> publishPurchase(@RequestBody PurchaseReq purchaseReq) {
        return agriculturalPurchaseInfoService.publishPurchase(purchaseReq.getPublisherId(), purchaseReq.getCategory(), purchaseReq.getPrice(), purchaseReq.getQuantity());
    }

    // 删除收购信息
    @RequestMapping(value = "/delPurchaseInfo")
    @ResponseBody
    public Response<String> delPurchaseInfo(@RequestBody IdReq idReq) {
        return agriculturalPurchaseInfoService.delPurchaseInfo(idReq.getId());
    }

    // 根据收购信息ID获取收购信息
    @RequestMapping(value = "/getPurchaseInfoById")
    @ResponseBody
    public Response<AgriculturalPurchaseInfo> getPurchaseInfoById(@RequestBody IdReq idReq) {
        return agriculturalPurchaseInfoService.getPurchaseInfoById(idReq.getId());
    }

    // 获取所有收购信息
    @RequestMapping(value = "/getAllPurchaseInfo")
    @ResponseBody
    public Response<List<AgriculturalPurchaseInfo>> getAllPurchaseInfo() {
        return agriculturalPurchaseInfoService.getAllPurchaseInfo();
    }

    // 根据发布人获取其发布的所有收购信息
    @RequestMapping(value = "/getPurchaseInfoByPublisherId")
    @ResponseBody
    public Response<List<AgriculturalPurchaseInfo>> getPurchaseInfoByPublisherId(@RequestBody IdReq idReq) {
        return agriculturalPurchaseInfoService.getPurchaseInfoByPublisherId(idReq.getId());
    }

    // 农民出售
    @RequestMapping(value = "/saleAgri")
    @ResponseBody
    public Response<String> saleAgri(@RequestBody SaleReq saleReq) {
        return agriculturalPurchaseInfoService.saleAgri(saleReq.getSellerId(), saleReq.getRelatedPurchaseId(), saleReq.getQuantity());
    }

    // 通过农民ID查询售出记录
    @RequestMapping(value = "/getAgriculturalSaleInfoBySellerId")
    @ResponseBody
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoBySellerId(@RequestBody IdReq idReq) {
        return agriculturalPurchaseInfoService.getAgriculturalSaleInfoBySellerId(idReq.getId());
    }

    // 通过收购人ID查询售出记录
    @RequestMapping(value = "/getAgriculturalSaleInfoByRelatedPurchaseId")
    @ResponseBody
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoByRelatedPurchaseId(@RequestBody IdReq idReq) {
        return agriculturalPurchaseInfoService.getAgriculturalSaleInfoByRelatedPurchaseId(idReq.getId());
    }

    // 通过收购记录ID查询售出记录
    @RequestMapping(value = "/getAgriculturalSaleInfoByPurchaseUserId")
    @ResponseBody
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoByPurchaseUserId(@RequestBody IdReq idReq) {
        return agriculturalPurchaseInfoService.getAgriculturalSaleInfoByPurchaseUserId(idReq.getId());
    }

    // 通过农民Name查询售出记录
    @RequestMapping(value = "/getAgriculturalSaleInfoBySellerName")
    @ResponseBody
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoBySellerName(@RequestBody NameReq nameReq) {
        return agriculturalPurchaseInfoService.getAgriculturalSaleInfoBySellerName(nameReq.getName());
    }


}
