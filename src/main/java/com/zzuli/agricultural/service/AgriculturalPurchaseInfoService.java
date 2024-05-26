package com.zzuli.agricultural.service;

import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.AgriculturalNewsMapper;
import com.zzuli.agricultural.mapper.AgriculturalPurchaseInfoMapper;
import com.zzuli.agricultural.mapper.AgriculturalSaleInfoMapper;
import com.zzuli.agricultural.model.AgriculturalNews;
import com.zzuli.agricultural.model.AgriculturalPurchaseInfo;
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
    public Response<String> publishPurchase(int publisherId, String category, double price, int quantity, int startTime, int endTime) {
        User userById = userServiceV2.getUserById(publisherId);
        if (userById == null || userById.getUserType() != UserTypeEnum.buyer.type) {
            return new Response<>("收购消息仅有收购商发布", 1);
        }
        if (StringUtils.isEmpty(category) || price <= 0 || quantity <= 0 || endTime < startTime) {
            return new Response<>("收购信息不准确，请检查", 1);
        }
        AgriculturalPurchaseInfo build = AgriculturalPurchaseInfo.builder().purchaserId(publisherId).purchaserName(userById.getUsername())
                .purchasePrice(price).purchaseCategory(category).purchaseQuantity(quantity).purchaseStartTime(startTime).purchaseEndTime(endTime)
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
    public Response<List<AgriculturalPurchaseInfo>> getNewsByPublisherId(int publisherId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("purchaserId", publisherId);
        List<AgriculturalPurchaseInfo> agriculturalPurchaseInfos = agriculturalPurchaseInfoMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(agriculturalPurchaseInfos)) {
            return new Response<>("查询失败", 1, Collections.emptyList());
        }
        return new Response<>("查询成功", 0, agriculturalPurchaseInfos);
    }



}
