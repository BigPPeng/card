import com.card.SpringBootApplicationStart;
import com.card.common.DateToolUtil;
import com.card.mapper.BlackListMapper;
import com.card.test.mysql.GoodsBlackList;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by cuihp on 2020/2/9.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationStart.class)
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @Resource
    private BlackListMapper blackListMapper;

    @org.junit.Test
    public void test() {
        logger.warn("test print----------------");
        int i = 10;
        while (i < 200) {
            testInsert(5000);
            i ++;
        }

        logger.warn("test print----------------");
    }

    private void testInsert(int size) {
        ArrayList<GoodsBlackList> objects = getGoodsBlackLists(size);
        long t1 = System.currentTimeMillis();
        int insert = blackListMapper.insert(objects);
        long t2 = System.currentTimeMillis();
        logger.warn("print insert count {}, time:{}", insert, t2-t1);
    }

    private ArrayList<GoodsBlackList> getGoodsBlackLists(int size) {
        ArrayList<GoodsBlackList> objects = Lists.newArrayList();
        for (int i = 0; i < size ; i++) {
            GoodsBlackList goodsBlackList = new GoodsBlackList();
            goodsBlackList.setMhotelId(getRaNum(8));
            goodsBlackList.setCreater(getCreater());
            Date createTime = new Date();
            goodsBlackList.setCreateTime(createTime);
            goodsBlackList.setCreateTimeString(DateToolUtil.format(DateToolUtil.YYYY_MM_DD_HH_MM_SS,createTime));
            goodsBlackList.setDistType(getRaNum(1));
            goodsBlackList.setGroupId(getRaNum(8));
            goodsBlackList.setRatePlanId(getRaNum(8));
            goodsBlackList.setShotelId(getRaNum(8));
            goodsBlackList.setSroomId(getRaNum(4));
            goodsBlackList.setStatus(getRaNum(1));
            goodsBlackList.setSupplierId(getRaNum(8));
            objects.add(goodsBlackList);
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