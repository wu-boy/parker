package com.wu.parker.jpamybatis.test;

import com.wu.parker.jpamybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wusq
 * @date: 2018/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testTransaction() throws Exception {
        userService.testTransaction();
    }

}
