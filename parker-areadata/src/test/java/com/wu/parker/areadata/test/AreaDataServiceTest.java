package com.wu.parker.areadata.test;

import com.wu.parker.areadata.AreaDataApplication;
import com.wu.parker.areadata.po.AreaData;
import com.wu.parker.areadata.service.AreaDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/11/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AreaDataApplication.class)
public class AreaDataServiceTest {

    @Autowired
    private AreaDataService areaDataService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testSave() throws Exception {
        areaDataService.save();
    }

    @Test
    public void testList() throws Exception {
        List<AreaData> list = areaDataService.list("86");
        list.forEach(o -> System.out.println(o.getName()));
    }

}
