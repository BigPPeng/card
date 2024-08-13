import com.SpringBootApplicationStart;
import com.alibaba.fastjson.JSON;
import com.zzuli.agricultural.common.DateToolUtil;
import com.zzuli.agricultural.common.Response;
import com.google.common.collect.Lists;
import com.zzuli.agricultural.model.*;
import com.zzuli.agricultural.service.RecruitmentService;
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
public class RecruitmentServiceTest {
    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private UserServiceV2 userServiceV2;

    @org.junit.Test
    public void testAddProduct() {

        List<User> administrators = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.administrators));
        System.out.println("administrators:  " + JSON.toJSONString(administrators));
        List<User> farmers = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.farmers));
        System.out.println("farmers:  " + JSON.toJSONString(farmers));

        for (User administrator : administrators) {
            recruitmentService.publishRecruitmentInfo(administrator.getId(), "木工招聘_" + administrator.getId(), "干木工活，一天10块",
                    DateToolUtil.getNow(), administrator.getId() + 10);
            recruitmentService.publishRecruitmentInfo(administrator.getId(), "瓦工招聘_" + administrator.getId(), "干木工活，一天10块",
                    DateToolUtil.getNow(), administrator.getId() + 10);
        }
        Response<List<RecruitmentInfo>> listResponse = recruitmentService.selectAllRecruitmentInfo();
        System.out.println("listResponse ：" + JSON.toJSONString(listResponse));

        List<RecruitmentInfo> data = listResponse.getData();
        for (User farmer : farmers) {
            for (RecruitmentInfo datum : data) {
                Response<String> stringResponse = recruitmentService.applyRecruitment(farmer.getId(), datum.getId());
                System.out.println("stringResponse: " + JSON.toJSONString(stringResponse));
            }
        }
    }

    @org.junit.Test
    public void testDele() {
        System.out.println("--"+JSON.toJSONString(recruitmentService.delRecruitmentInfo(11)));
        System.out.println("--"+JSON.toJSONString(recruitmentService.delRecruitmentInfo(18)));
        System.out.println("--"+JSON.toJSONString(recruitmentService.delRecruitmentApplicationRecord(39)));
        System.out.println("--"+JSON.toJSONString(recruitmentService.delRecruitmentApplicationRecord(69)));
    }

    @org.junit.Test
    public void testGetAll() {
        Response<List<RecruitmentInfo>> listResponse1 = recruitmentService.selectAllRecruitmentInfo();
        System.out.println("-listResponse1-----"+JSON.toJSONString(listResponse1));

        Response<List<RecruitmentApplicationRecord>> listResponse = recruitmentService.selectAllRecruitmentApplicationRecord();
        System.out.println("-listResponse-----"+JSON.toJSONString(listResponse));

        Response<List<RecruitmentApplicationRecord>> listResponse2 = recruitmentService.selectRecruitmentApplicationRecordByRecruitmentInfoId(33);
        System.out.println("-listResponse2-----"+JSON.toJSONString(listResponse2));

        Response<List<RecruitmentApplicationRecord>> listResponse3 = recruitmentService.selectRecruitmentApplicationRecordByRecruitmentUserId(43);
        System.out.println("-listResponse3-----"+JSON.toJSONString(listResponse3));

        Response<List<RecruitmentApplicationRecord>> listResponse4 = recruitmentService.selectRecruitmentApplicationRecordByApplyUserId(41);
        System.out.println("-listResponse4-----"+JSON.toJSONString(listResponse4));

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