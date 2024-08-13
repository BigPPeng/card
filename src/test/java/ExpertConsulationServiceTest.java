import com.SpringBootApplicationStart;
import com.alibaba.fastjson.JSON;
import com.zzuli.agricultural.common.Response;
import com.google.common.collect.Lists;
import com.zzuli.agricultural.model.*;
import com.zzuli.agricultural.service.ExpertConsultationService;
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
public class ExpertConsulationServiceTest {
    @Autowired
    private ExpertConsultationService expertConsultationService;

    @Autowired
    private UserServiceV2 userServiceV2;

    @org.junit.Test
    public void testAddProduct() {
        List<User> administrators = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.administrators));
        System.out.println("ssssssssssss administrators:  " + JSON.toJSONString(administrators));
        List<User> farmers = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.farmers));
        System.out.println("ssssssssssss farmers:  " + JSON.toJSONString(farmers));
        for (User farmer : farmers) {
//            if (farmer.getId() % 2 == 0) {
                expertConsultationService.publishConsultation(farmer.getId(), "小麦发芽怎么处理", "俺家小麦发芽了，怎么处理啊", "小麦");
//            } else {
//                expertConsultationService.publishConsultation(farmer.getId(), "花生选种子", "花生选种子，怎么选的最好", "花生");
//            }
        }
        Response<List<ExpertConsultation>> allConsultation = expertConsultationService.getAllConsultation();
        List<ExpertConsultation> data = allConsultation.getData();
        System.out.println("ssssssssssss"+JSON.toJSONString(data));

        Response<ExpertConsultation> consultationById = expertConsultationService.getConsultationById(data.get(0).getId());
        System.out.println("ssssssssssss"+JSON.toJSONString(consultationById));


    }

    @org.junit.Test
    public void testDele() {
        Response<List<ExpertConsultation>> allConsultation = expertConsultationService.getAllConsultation();
        List<ExpertConsultation> data = allConsultation.getData();
        System.out.println("ssssssssssss"+JSON.toJSONString(data));

        Response<String> stringResponse = expertConsultationService.delConsultation(data.get(0).getId());
        System.out.println("ssssssssssss"+JSON.toJSONString(stringResponse));
    }

    @org.junit.Test
    public void testRes() {
        Response<List<ExpertConsultation>> allConsultation = expertConsultationService.getAllConsultation();
        List<ExpertConsultation> data = allConsultation.getData();
        System.out.println("ssssssssssss"+JSON.toJSONString(data));
        Response<String> stringResponse = expertConsultationService.responseConsultation(46, data.get(0).getId(), "赶紧吃");
        System.out.println("ssssssssssss"+JSON.toJSONString(stringResponse));
        Response<String> stringResponse1 = expertConsultationService.responseConsultation(46, data.get(0).getId(), "赶紧吃1");
        System.out.println("ssssssssssss"+JSON.toJSONString(stringResponse1));
    }

    @org.junit.Test
    public void testSelect() {
        Response<List<ExpertConsultation>> consultationByPublisherId = expertConsultationService.getConsultationByPublisherId(49);
        System.out.println("sssssssss"+JSON.toJSONString(consultationByPublisherId));
        Response<List<ExpertConsultation>> serviceConsultationByResponderId = expertConsultationService.getConsultationByResponderId(46);
        System.out.println("sssssssss"+JSON.toJSONString(serviceConsultationByResponderId));
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