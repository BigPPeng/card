package com.card.mapper;

import com.card.model.CardInfo;
import com.card.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
@Component
public interface CardInfoMapper {

    int deleteByPrimaryKey(long id);

    long insert(CardInfo record);

    CardInfo selectByPrimaryKey(Long id);

    List<CardInfo> selectAll();

    List<CardInfo> selectByParams(Map<String,Object> params);

    int updateByPrimaryKey(CardInfo record);

    int getCount(Map<String,Object> params);
}
