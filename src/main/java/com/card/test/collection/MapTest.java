package com.card.test.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by cuihp on 2020/4/14.
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,String> hotelAdvanceCouponPromotions = Maps.newHashMap();
        hotelAdvanceCouponPromotions.put("A","B");
        hotelAdvanceCouponPromotions.put("B",null);
        hotelAdvanceCouponPromotions.put("C","C");
        hotelAdvanceCouponPromotions.put("D",null);
        System.out.println(JSON.toJSONString(hotelAdvanceCouponPromotions));
    }

}
