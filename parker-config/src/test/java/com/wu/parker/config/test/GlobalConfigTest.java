package com.wu.parker.config.test;

import com.wu.parker.config.config.GlobalConfig;
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
public class GlobalConfigTest {

    @Autowired
    private GlobalConfig globalConfig;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() throws Exception {
        System.out.println(globalConfig.getName());
    }

}
