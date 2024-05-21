package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.AgriculturalSaleInfo;

import java.util.List;
import java.util.Map;

public interface AgriculturalSaleInfoMapper {
    int insertAgriculturalSaleInfo(AgriculturalSaleInfo agriculturalSaleInfo);
    int updateAgriculturalSaleInfo(AgriculturalSaleInfo agriculturalSaleInfo);
    AgriculturalSaleInfo selectAgriculturalSaleInfoById(Integer id);
    List<AgriculturalSaleInfo> selectAllAgriculturalSaleInfos();
    int deleteAgriculturalSaleInfo(Integer id);
    List<AgriculturalSaleInfo> selectByParams(Map<String,Object> params);
}
