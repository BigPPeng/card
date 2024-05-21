package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.AgriculturalPurchaseInfo;

import java.util.List;
import java.util.Map;

public interface AgriculturalPurchaseInfoMapper {
    int insertAgriculturalPurchaseInfo(AgriculturalPurchaseInfo agriculturalPurchaseInfo);
    int updateAgriculturalPurchaseInfo(AgriculturalPurchaseInfo agriculturalPurchaseInfo);
    AgriculturalPurchaseInfo selectAgriculturalPurchaseInfoById(Integer id);
    List<AgriculturalPurchaseInfo> selectAllAgriculturalPurchaseInfos();
    int deleteAgriculturalPurchaseInfo(Integer id);
    List<AgriculturalPurchaseInfo> selectByParams(Map<String,Object> params);
}
