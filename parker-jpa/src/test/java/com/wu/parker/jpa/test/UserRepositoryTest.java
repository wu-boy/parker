package com.wu.parker.jpa.test;

import com.wu.parker.jpa.dao.UserRepository;
import com.wu.parker.jpa.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wusq
 * @date: 2018/11/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userRepository.save(user);
    }
}
