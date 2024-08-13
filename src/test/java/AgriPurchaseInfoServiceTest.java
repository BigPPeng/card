import com.SpringBootApplicationStart;
import com.alibaba.fastjson.JSON;
import com.zzuli.agricultural.common.Response;
import com.google.common.collect.Lists;
import com.zzuli.agricultural.model.AgriculturalPurchaseInfo;
import com.zzuli.agricultural.model.AgriculturalSaleInfo;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import com.zzuli.agricultural.service.AgriculturalPurchaseInfoService;
import com.zzuli.agricultural.service.UserServiceV2;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by cuihp on 2020/2/9.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationStart.class)
public class AgriPurchaseInfoServiceTest {
    @Autowired
    private AgriculturalPurchaseInfoService recruitmentService;

    @Autowired
    private UserServiceV2 userServiceV2;

    @org.junit.Test
    public void testAddProduct() {

        List<User> buyers = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.buyer));
        System.out.println("administrators:  " + JSON.toJSONString(buyers));

        List<User> farmers = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.farmers));
        System.out.println("farmers:  " + JSON.toJSONString(farmers));

        for (int i = 0; i < buyers.size(); i++) {
            recruitmentService.publishPurchase(buyers.get(i).getId(), "小麦" + i, 10.5, 100);
        }
        Response<List<AgriculturalPurchaseInfo>> purchaseInfoByPublisherId = recruitmentService.getPurchaseInfoByPublisherId(buyers.get(0).getId());
        System.out.println("purchaseInfoByPublisherId ：" + JSON.toJSONString(purchaseInfoByPublisherId));

        List<AgriculturalPurchaseInfo> data = purchaseInfoByPublisherId.getData();
        for (User farmer : farmers) {
            for (int i = 0; i < data.size(); i++) {
                Response<String> stringResponse = recruitmentService.saleAgri(farmer.getId(), data.get(i).getId(), 10 + i);
                System.out.println("stringResponse: " + JSON.toJSONString(stringResponse));
            }
        }
    }

    @org.junit.Test
    public void testDele() {
        Response<String> stringResponse = recruitmentService.delPurchaseInfo(2);
        System.out.println("--------" + JSON.toJSONString(stringResponse));
        Response<String> stringResponse1 = recruitmentService.delPurchaseInfo(5);
        System.out.println("--------" + JSON.toJSONString(stringResponse1));
    }

    @org.junit.Test
    public void testGetAll() {
        Response<List<AgriculturalPurchaseInfo>> purchaseInfoByPublisherId = recruitmentService.getPurchaseInfoByPublisherId(71);
        System.out.println("--------" + JSON.toJSONString(purchaseInfoByPublisherId));
        Response<List<AgriculturalPurchaseInfo>> pa = recruitmentService.getAllPurchaseInfo();
        System.out.println("--------" + JSON.toJSONString(pa));
        Response<AgriculturalPurchaseInfo> purchaseInfoById = recruitmentService.getPurchaseInfoById(2);
        System.out.println("--------" + JSON.toJSONString(purchaseInfoById));


        Response<List<AgriculturalSaleInfo>> xiao8 = recruitmentService.getAgriculturalSaleInfoBySellerName("xiao_8");
        System.out.println("--------" + JSON.toJSONString(xiao8));

        Response<List<AgriculturalSaleInfo>> agriculturalSaleInfoBySellerId = recruitmentService.getAgriculturalSaleInfoBySellerId(41);
        System.out.println("--------" + JSON.toJSONString(agriculturalSaleInfoBySellerId));

        Response<List<AgriculturalSaleInfo>> agriculturalSaleInfoByPurchaseUserId = recruitmentService.getAgriculturalSaleInfoByPurchaseUserId(71);
        System.out.println("--------" + JSON.toJSONString(agriculturalSaleInfoByPurchaseUserId));
        System.out.println("--------" + agriculturalSaleInfoByPurchaseUserId.getData().size());

    }

    @org.junit.Test
    public void testBuy() {

    }


    @org.junit.Test
    public void testRent() {

    }

    @org.junit.Test
    public void test5() {
    }

    @org.junit.Test
    public void test6() {
    }


    @org.junit.Test
    public void test7() {
    }

}