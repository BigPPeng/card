import com.SpringBootApplicationStart;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import com.zzuli.agricultural.service.UserServiceV2;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by cuihp on 2020/2/9.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationStart.class)
public class UserServiceV2Test {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceV2Test.class);

    @Autowired
    private UserServiceV2 userServiceV2;

    @org.junit.Test
    public void test() {
        String s = userServiceV2.userRegister("TestAdd", "112", "12@qq.com", "sasa", UserTypeEnum.farmers);
        System.out.println(s);
    }

    @org.junit.Test
    public void test1() {
        String s = userServiceV2.addAdministrators(56,"addAdministrators", "112", "12@qq.com", "sasa");
        System.out.println(s);
    }

    @org.junit.Test
    public void test2() {
        String s = userServiceV2.addStoreOwner(56,"addStoreOwner", "112", "12@qq.com", "sasa");
        System.out.println(s);
    }

    @org.junit.Test
    public void test3() {
        String s = userServiceV2.addAgriculturalTechnologyExperts(56,"addAgriculturalTechnologyExperts", "112", "12@qq.com", "sasa");
        System.out.println(s);
    }


    @org.junit.Test
    public void test4() {
        List<User> allUserByType = userServiceV2.getAllUserByType(null);
        System.out.println("---------all"+allUserByType.size());
        List<User> fas = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.farmers));
        System.out.println("---------fas"+fas.size());
        System.out.println("---------fas"+fas.get(0));
        User allUserById = userServiceV2.getUserById(45);
        System.out.println("---------fass"+allUserById);
    }

    @org.junit.Test
    public void test5() {
        String s = userServiceV2.userRegister("TestAdd", "112", "12@qq.com", "sasa", UserTypeEnum.farmers);
        System.out.println(s);
        Assert.assertEquals("用户名重复，请求修用户名", s);
    }

    @org.junit.Test
    public void test6() {
        User user = userServiceV2.userLogin("xiao_19", "pass");
        Assert.assertNotNull(user);
        User user2 = userServiceV2.userLogin("xiao_19", "asdss");
        Assert.assertNotNull(user2);
    }


    @org.junit.Test
    public void test7() {
        User allUserById = userServiceV2.getUserById(45);
        System.out.println("---------fass"+allUserById);
        userServiceV2.deleteUser(45);
        User allUserById1 = userServiceV2.getUserById(45);
        System.out.println("---------fass"+allUserById1);
    }

}