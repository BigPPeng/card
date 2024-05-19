import com.alibaba.fastjson.JSON;
import com.SpringBootApplicationStart;
import com.card.common.DateToolUtil;
import com.zzuli.agricultural.mapper.UserMapper2;
import com.zzuli.agricultural.model.User;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cuihp on 2020/2/9.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationStart.class)
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @Resource
    private UserMapper2 userMapper2;

    @org.junit.Test
    public void test() {
        logger.warn("test print----------------");
        testInsert(20);
        List<User> users = userMapper2.selectAllUsers();
        System.out.println(JSON.toJSONString(users));
//        for (User user : users) {
//            userMapper2.deleteUser(user.getId());
//        }

        logger.warn("test print----------------");
    }

    private void testInsert(int size) {
        ArrayList<User> objects = getUserLists(size);
        long t1 = System.currentTimeMillis();
        int insert = 0;
        for (User object : objects) {
             userMapper2.insertUser(object);
             insert++;
        }

        long t2 = System.currentTimeMillis();
        logger.warn("print insert count {}, time:{}", insert, t2-t1);
    }

    private ArrayList<User> getUserLists(int size) {
        ArrayList<User> objects = Lists.newArrayList();
        for (int i = 0; i < size ; i++) {
            User pass = User.builder()
                    .username("xiao_"+i)
                    .userStatus(1).isActive(1).email("128922@qq.com").phone("13020292")
                    .password("pass")
                    .registerTime(DateToolUtil.getNow())
                    .userType((i % 4) + 1)
                    .build();
            objects.add(pass);
        }
        return objects;
    }


    public static final int[] BASE_NUMS = {0,1,2,3,4,5,6,7,8,9};

    private static int getRaNum(int length) {
        Random random = new Random();

        StringBuilder res = new StringBuilder();
        for (int i = 0; i< length ; i++) {
            int j = random.nextInt() % BASE_NUMS.length;
            j = j < 0 ? -j : j;
            res.append(BASE_NUMS[j]);
        }
        return Integer.valueOf(res.toString());
    }



    public static final String a = "右键单击我的电脑属高级系统设置高级环境变量点击系统变量下的新建按钮输入变量名输入变量值选择系统变量中的点击编辑按钮" +
            "在变量值中添加变量值注意是在原有变量值后面加上这个变量用隔开不能删除原来的变量值版权声明本文为CSDN博主的原创文章" +
            "遵循版权协议转载请附上原文出处链接及本声明原文链接";
    public static final char[] aChars = new char[140];
    static {
        char[] chars = a.toCharArray();
        System.arraycopy(chars, 0, aChars, 0, chars.length);
    }




    private static String getCreater() {
        Random random = new Random();
        int i = random.nextInt() % aChars.length;
        i = i < 0 ? -i : i;
        int j = random.nextInt() % aChars.length;
        j = j < 0 ? -j : j;
        int k = random.nextInt() % aChars.length;
        k = k < 0 ? -k : k;
        return aChars[i] + "" + aChars[j] + "" + aChars[k] + "";
    }

}