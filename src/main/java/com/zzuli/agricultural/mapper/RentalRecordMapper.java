package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.ProductOrder;
import com.zzuli.agricultural.model.RentalRecord;

import java.util.List;
import java.util.Map;

public interface RentalRecordMapper {
    int insertRentalRecord(RentalRecord rentalRecord);
    int deleteRentalRecord(Integer id);
    int updateRentalRecord(RentalRecord rentalRecord);
    RentalRecord selectRentalRecord(Integer id);
    List<RentalRecord> selectAllRentalRecords();
    List<RentalRecord> selectByParams(Map<String, Object> param);
}
