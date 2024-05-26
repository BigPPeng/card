import com.SpringBootApplicationStart;
import com.alibaba.fastjson.JSON;
import com.card.common.Response;
import com.google.common.collect.Lists;
import com.zzuli.agricultural.model.AgriculturalNews;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import com.zzuli.agricultural.service.AgriNewsService;
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
public class ArgiServiceTest {
    @Autowired
    private AgriNewsService agriNewsService;

    @Autowired
    private UserServiceV2 userServiceV2;

    @org.junit.Test
    public void testAdd() {
        List<User> administrators = userServiceV2.getAllUserByType(Lists.newArrayList(UserTypeEnum.administrators));
        System.out.println("ssssssssssss administrators:  " + JSON.toJSONString(administrators));

        for (User administrator : administrators) {
            agriNewsService.publishNews(administrator.getId(), "小麦大涨价", "小麦", "小麦大涨价，不要等了，赶紧卖吧", "中国农业网");
            agriNewsService.publishNews(administrator.getId(), "大豆大涨价", "大豆", "大豆大涨价，不要等了，今年种大豆", "美国农业网");
        }


        Response<List<AgriculturalNews>> allNews = agriNewsService.getAllNews();
        List<AgriculturalNews> data = allNews.getData();
        System.out.println("ssssssssssss"+JSON.toJSONString(data));

        Response<AgriculturalNews> newsById = agriNewsService.getNewsById(data.get(0).getId());
        System.out.println("ssssssssssss"+JSON.toJSONString(newsById));


        Response<List<AgriculturalNews>> newsByPublisherId = agriNewsService.getNewsByPublisherId(administrators.get(0).getId());
        System.out.println("ssssssssssss"+JSON.toJSONString(newsByPublisherId));
    }

    @org.junit.Test
    public void testDele() {
        Response<String> stringResponse = agriNewsService.delNews(33);
    }

    @org.junit.Test
    public void testRes() {

    }

    @org.junit.Test
    public void testSelect() {

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