package com.wu.parker.mybatis.test;

import com.wu.parker.mybatis.dao.UserMapper;
import com.wu.parker.mybatis.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wusq
 * @date: 2019/1/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setId("1");
        user.setUsername("admin");
        user.setPassword("admin");
        System.out.println(userMapper.save(user));
    }

    @Test
    public void testBatchDelete() throws Exception {
        String[] ids = new String[1];
        ids[0] = "1";
        userMapper.batchDelete(ids);
    }

    @Test
    public void testExecuteManySql() throws Exception {
        userMapper.executeManySql();
    }
}
