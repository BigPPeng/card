package com.card.mapper;

import com.card.test.mysql.GoodsBlackList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihp on 2020/2/9.
 */
@Component
public interface BlackListMapper {
    /**
     * 查询黑名单.
     *
     * @param params 参数
     * @return 黑名单列表
     */
    List<GoodsBlackList> queryBlackList(Map<String, Object> params);

    /**
     * 查询黑名单总数.
     *
     * @param params 参数
     * @return 黑名单总数
     */
    int queryCount(Map<String, Object> params);


    // 插入数据
    int insert(@Param(value = "list") List<GoodsBlackList> list);
}
