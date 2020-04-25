package com.card.test.thrift.client;

import com.card.test.thrift.GsonUtil;
import com.elong.hotel.goods.ds.thrift.ProductSearchService;
import com.elong.hotel.goods.ds.thrift.SearchRequest;
import com.elong.hotel.goods.ds.thrift.SearchResponse;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

public class HelloServiceClient {

//    public static void main(String[] args) {
//        String client = "client";
//        for (int i = 0; i < 10; i++) {
//            // SimpleServer || ThreadPoolServer
//            Thread thread = new Thread(new ThriftClientThread(client + i));
//            Thread thread = new Thread(new ThriftNonblockingClientThread(client + i));
//            Thread thread = new Thread(new ThriftHsHaClientThread(client + i));
//            thread.start();
//            System.out.println(client + i + " start ...");
//        }
//    }

    public static void main(String[] args) {
        String client = "client";
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new AsyncThriftNonblockingClientThread(client + i));
            thread.start();
            System.out.println(client + i + " start ...");
        }
    }

//    public static void main(String[] args) {
//
//        try {
//            TFramedTransport transport = new TFramedTransport(new TSocket("10.161.118.88", 5800, 10000));
//            ProductSearchService.Client dsClient = new ProductSearchService.Client(new TCompactProtocol(transport));
//            transport.open();
//            String gson = "{\"mhotel_ids\":[90000064],\"booking_datetime\":1578570263,\"checkin_date\":1578585600,\"checkout_date\":1578672000,\"booking_channel\":16,\"sell_channel\":2,\"customer_level\":1024,\"is_limit_timesale\":false,\"price_type\":0,\"product_type\":33964065,\"settlement_type\":0,\"online_search_type\":[0,1,2],\"promotion_type\":0,\"order_from_id\":50002,\"proxy_id\":\"ZD\",\"promotion_channel_code\":\"1041\",\"need7daygift\":false,\"codes\":[1,2,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46],\"return_noinv_or_noprice_product\":true,\"return_has_coupon_hotel\":false,\"return_has_no_danbao_hotel\":false,\"return_has_yufu_hotel\":false,\"onlydebug\":true,\"return_has_timerush_product_hotel\":false,\"return_has_lianzhu_pro_hotel\":false,\"return_has_zaoding_pro_hotel\":false,\"request_origin\":3,\"return_longcuionly_hotel\":false,\"return_has_hongbao_hotel\":false,\"judge_only_has_product\":false,\"half_discount_promotion\":true,\"search_method\":4,\"return_has_discount_promotion_hotel\":false,\"search_id\":0,\"price_sub_coupon\":true,\"return_freesale_msg\":true,\"mhotel_attrs\":[{\"mhotel_id\":50101002,\"mroom_filter\":false,\"__isset_bitfield\":3}],\"return_has_allbuyroom_hotel\":false,\"return_has_manjian_hotel\":false,\"language\":0,\"discount_method\":0,\"return_discount_hotel\":0,\"min_price_calc_with_halfdiscount_pro\":1,\"botao_customer_level\":2,\"use_botao_promotion\":false,\"use_day_promotion\":true,\"only_consider_salable\":true,\"return_has_memberbenefits_hotel\":false,\"booking_menu\":\"kGeneralMenu\",\"min_price_excluded_products\":32,\"grandson\":{\"codes\":[2000]},\"is_new_hongbao\":true,\"return_has_breakfasts_hotel\":0,\"return_has_xianfu_hotel\":false,\"request_type\":2,\"searchFrom\":2,\"traceId\":\"qwqwa737128a-3f5c-48c5-98f4-bba036354610\",\"onlymajiadebug\":true,\"return_has_resale_hotel\":false,\"resaleIsAsMinPrice\":true,\"channel\":\"MobileApi\",\"searchFromEnd\":2,\"is_failfast_enable\":false,\"has_majia\":true,\"has_zydj\":false,\"majia_zydj_switch\":0,\"return_hotel_ticket_product\":true,\"return_price_range_statistic\":false,\"return_exclusive_hotel_info\":false,\"return_new_botao_member_product\":false,\"return_has_gdgf_hotel\":false,\"gs_request_time\":1525954941878,\"has_coupon_enhance\":true,\"member_type\":0,\"app_from\":1,\"return_coupon_enhance\":true,\"return_pricing_log\":true,\"hotel_ticket_product_need_promotion\":false,\"main_detail_mhotel\":50101002,\"search_type\":1,\"search_days_type\":1,\"return_shopper_product\":true,\"disable_real_time_coupon_calc\":false,\"card_number\":\"0\",\"idfa\":\"869634023090901||8696E4013000991\",\"return_booking_product\":true,\"ctrip_vouch_prepay_special\":true,\"latest_check_in_time\":1525962006,\"is_open_price_sub_without_cashback\":false,\"not_return_botao_member_co_product\":false,\"use_goods_room_merger\":false,\"product_type_is_not_return\":false,\"not_return_only_4_wechat_product\":false,\"shopper_is_close_real_time\":false,\"ctrip_hotel_level\":0,\"return_selling_hongbao\":true,\"__isset_bit_vector\":[1,1,1,1,1,1,0,0,1,0,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1,0,0,1,1,1,1,0,0,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,1,0,0,1]}";
//            SearchRequest searchReq = GsonUtil.parseObj(gson, SearchRequest.class);
//            searchReq.setOnlydebug(true);
//            SearchResponse response = dsClient.searchProducts(searchReq);
//            transport.close();
//            System.out.println(GsonUtil.toJson(response));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }



}
