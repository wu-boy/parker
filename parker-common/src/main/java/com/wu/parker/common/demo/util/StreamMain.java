package com.wu.parker.common.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wusq
 * @date 2019/4/19
 */
public class StreamMain {

    static List<UserDemo> userDemoList;

    /**
     * 初始化集合
     */
    static void init(){

        userDemoList = new ArrayList<>();

        UserDemo a = new UserDemo();
        a.setId("a");
        a.setName("user-a");
        a.setAge(28);
        userDemoList.add(a);

        UserDemo b = new UserDemo();
        b.setId("b");
        b.setName("user-b");
        b.setAge(18);
        userDemoList.add(b);

        UserDemo c = new UserDemo();
        c.setId("c");
        c.setName("user-c");
        c.setAge(38);
        userDemoList.add(c);

    }

    /**
     * 拼接ID
     */
    static void joinId(){
        String ids = userDemoList.stream().map(UserDemo::getId).collect(Collectors.joining(","));
        System.out.println(ids); // 输出a,b,c
    }

    public static void main(String[] args) {

        init();

        joinId();
    }
}
