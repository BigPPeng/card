package com.card.mapper;

import com.card.model.BillRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by hongpeng.cui on 2019/3/27.
 */
@Component
public interface BillRecordMapper {

    long insert(BillRecord record);

    BillRecord selectByPrimaryKey(Long id);

    List<BillRecord> selectByParams(Map<String,Object> params);

    int updateByPrimaryKey(BillRecord record);

    int getCount(Map<String,Object> params);
}
