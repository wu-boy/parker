package com.wu.parker.redis.test;

import com.wu.parker.redis.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author: wusq
 * @date: 2018/12/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations valueOperations;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() throws Exception {

        // 存取字符串
        // 建议存储数据时，设置过期时间
        valueOperations.set("test", "存个字符串", 9, TimeUnit.MINUTES);
        System.out.println(redisTemplate.hasKey("test"));
        System.out.println(valueOperations.get("test"));

        // 存取对象
        User user = new User();
        user.setId("1");
        user.setUsername("admin");
        user.setPassword("123456");
        valueOperations.set("user", user, 1, TimeUnit.HOURS);
        User o = (User) valueOperations.get("user");
        System.out.println(o.getUsername());
    }
}
