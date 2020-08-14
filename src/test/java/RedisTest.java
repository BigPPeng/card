import com.card.SpringBootApplicationStart;
import com.card.model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by cuihp on 2020/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationStart.class)
public class RedisTest {

    @Resource
    private RedisTemplate<String,Object> template;

    @org.junit.Test
    public void test() {
        ValueOperations<String, Object> redisString = template.opsForValue();
        // SET key value: 设置指定 key 的值
        redisString.set("strKey1", "hello spring boot redis");
        // GET key: 获取指定 key 的值
        String value = (String) redisString.get("strKey1");
        System.out.println(value);

        redisString.set("strKey2", new User());
        User user = (User) redisString.get("strKey2");
        System.out.println(user);



    }


}
