package com.zzuli.agricultural.service;

import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.AgriculturalPurchaseInfoMapper;
import com.zzuli.agricultural.mapper.AgriculturalSaleInfoMapper;
import com.zzuli.agricultural.model.AgriculturalPurchaseInfo;
import com.zzuli.agricultural.model.AgriculturalSaleInfo;
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
public class AgriculturalPurchaseInfoService {
    @Autowired
    private AgriculturalPurchaseInfoMapper agriculturalPurchaseInfoMapper;

    @Autowired
    private AgriculturalSaleInfoMapper agriculturalSaleInfoMapper;

    @Autowired
    private UserServiceV2 userServiceV2;

    /**
     * 发布收购信息
     */
    public Response<String> publishPurchase(int publisherId, String category, double price, int quantity) {
        User userById = userServiceV2.getUserById(publisherId);
        if (userById == null || userById.getUserType() != UserTypeEnum.buyer.type) {
            return new Response<>("收购消息仅由收购商发布", 1);
        }
        if (StringUtils.isEmpty(category) || price <= 0 || quantity <= 0) {
            return new Response<>("收购信息不准确，请检查", 1);
        }
        AgriculturalPurchaseInfo build = AgriculturalPurchaseInfo.builder().purchaserId(publisherId).purchaserName(userById.getUsername())
                .purchasePrice(price).purchaseCategory(category).purchaseQuantity(quantity)
                .purchaseStartTime(DateToolUtil.getNow()).purchaseEndTime(DateToolUtil.getNow() + 365 * 24 * 60 * 60)
                .publishTime(DateToolUtil.getNow()).build();
        int i = agriculturalPurchaseInfoMapper.insertAgriculturalPurchaseInfo(build);
        if (i == 1) {
            return new Response<>("发布收购信息成功", 0);
        }
        return new Response<>("发布收购信息失败", 1);
    }

    /**
     * 删除收购信息
     */
    public Response<String> delPurchaseInfo(int id) {
        int i = agriculturalPurchaseInfoMapper.deleteAgriculturalPurchaseInfo(id);
        if (i == 1) {
            return new Response<>("删除收购信息成功", 0);
        }
        return new Response<>("删除收购信息失败", 1);
    }

    /**
     * 根据id查询收购信息
     */
    public Response<AgriculturalPurchaseInfo> getPurchaseInfoById(int id) {
        AgriculturalPurchaseInfo purchaseInfo = agriculturalPurchaseInfoMapper.selectAgriculturalPurchaseInfoById(id);
        if (purchaseInfo == null) {
            return new Response<>("查询失败，无收购信息", 1);
        }
        return new Response<>("查询成功", 0, purchaseInfo);
    }

    /**
     * 查询所有收购信息
     */
    public Response<List<AgriculturalPurchaseInfo>> getAllPurchaseInfo() {
        List<AgriculturalPurchaseInfo> purchaseInfos = agriculturalPurchaseInfoMapper.selectAllAgriculturalPurchaseInfos();
        if (CollectionUtils.isEmpty(purchaseInfos)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, purchaseInfos);
    }

    /**
     * 根据发布人id查询所有咨询
     */
    public Response<List<AgriculturalPurchaseInfo>> getPurchaseInfoByPublisherId(int publisherId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("purchaserId", publisherId);
        List<AgriculturalPurchaseInfo> agriculturalPurchaseInfos = agriculturalPurchaseInfoMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(agriculturalPurchaseInfos)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, agriculturalPurchaseInfos);
    }

    /**
     * 农产品售卖记录
     */
    public Response<String> saleAgri(int sellerId, int relatedPurchaseId, int quantity) {
        User userById = userServiceV2.getUserById(sellerId);
        if (userById == null || userById.getUserType() != UserTypeEnum.farmers.type) {
            return new Response<>("农产品仅有农民售卖", 1);
        }
        Response<AgriculturalPurchaseInfo> purchaseInfoById = getPurchaseInfoById(relatedPurchaseId);
        if (purchaseInfoById.getStatus() != 0) {
            return new Response<>("收购信息不准确，请检查", 1);
        }

        AgriculturalPurchaseInfo purchaseInfo = purchaseInfoById.getData();
        AgriculturalSaleInfo agriculturalSaleInfo = AgriculturalSaleInfo.builder().sellerUserId(sellerId).sellerUserName(userById.getUsername())
                .saleTime(DateToolUtil.getNow()).relatedPurchaseId(relatedPurchaseId).saleQuantity(quantity)
                .salePrice(purchaseInfo.getPurchasePrice()).totalSalePrice(purchaseInfo.getPurchasePrice() * quantity).build();

        int saleInfo = agriculturalSaleInfoMapper.insertAgriculturalSaleInfo(agriculturalSaleInfo);
        if (saleInfo == 1) {
            return new Response<>("农产品售卖成功", 0);
        }
        return new Response<>("农产品售卖失败", 1);
    }

    /**
     * 通过农民ID查询售出记录
     */
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoBySellerId(int sellerId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("sellerUserId", sellerId);
        List<AgriculturalSaleInfo> agriculturalSaleInfos = agriculturalSaleInfoMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(agriculturalSaleInfos)) {
            return new Response<>("查询指定农民售卖记录失败", 0, Collections.emptyList());
        }
        return new Response<>("查询指定农民售卖记录成功", 0, agriculturalSaleInfos);
    }

    /**
     * 通过农民Name查询售出记录
     */
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoBySellerName(String sellerName) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("sellerUserName", sellerName);
        List<AgriculturalSaleInfo> agriculturalSaleInfos = agriculturalSaleInfoMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(agriculturalSaleInfos)) {
            return new Response<>("查询指定农民售卖记录成功", 0, Collections.emptyList());
        }
        return new Response<>("查询指定农民售卖记录成功", 0, agriculturalSaleInfos);
    }

    /**
     * 通过收购人ID查询售出记录
     */
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoByRelatedPurchaseId(int purchaseInfoId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("relatedPurchaseId", purchaseInfoId);
        List<AgriculturalSaleInfo> agriculturalSaleInfos = agriculturalSaleInfoMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(agriculturalSaleInfos)) {
            return new Response<>("查询指定收购信息对应的售卖记录成功", 0, Collections.emptyList());
        }
        return new Response<>("查询指定收购信息对应的售卖记录成功", 0, agriculturalSaleInfos);
    }

    /**
     * 通过收购记录ID查询售出记录
     */
    public Response<List<AgriculturalSaleInfo>> getAgriculturalSaleInfoByPurchaseUserId(int purchaseUserId) {
        Response<List<AgriculturalPurchaseInfo>> purchaseInfoByPublisherId = getPurchaseInfoByPublisherId(purchaseUserId);
        if (purchaseInfoByPublisherId.getStatus() != 0) {
            return new Response<>("查询指定收购商发布的收购信息对应的售卖记录失败", 1);
        }

        List<AgriculturalSaleInfo> all = Lists.newArrayList();
        for (AgriculturalPurchaseInfo datum : purchaseInfoByPublisherId.getData()) {
            Response<List<AgriculturalSaleInfo>> agriculturalSaleInfoByRelatedPurchaseId = getAgriculturalSaleInfoByRelatedPurchaseId(datum.getId());
            if (agriculturalSaleInfoByRelatedPurchaseId.getStatus() == 0) {
                all.addAll(agriculturalSaleInfoByRelatedPurchaseId.getData());
            }
        }
        return new Response<>("查询指定收购商发布的收购信息对应的售卖记录成功", 0, all);
    }

}
