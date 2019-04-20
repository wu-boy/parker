package com.wu.parker.common.demo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 函数式编程例子
 * @author wusq
 * @date 2019/4/20
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
     * 过滤年龄小于20的数据
     */
    static void filter(){
        userDemoList = userDemoList.stream().filter(o -> o.getAge() < 20).collect(Collectors.toList());
    }

    /**
     * 获取id的List
     */
    static void getIdList(){
        List<String> list = userDemoList.stream().map(UserDemo::getId).collect(Collectors.toList());
        list.forEach(o -> System.out.println(o));
    }

    /**
     * 拼接ID
     */
    static void joinId(){

        String ids = userDemoList.stream().map(UserDemo::getId).collect(Collectors.joining(","));
        System.out.println(ids); // 输出a,b,c

        // 加上前后缀
        ids = userDemoList.stream().map(UserDemo::getId).collect(
                Collectors.joining(",", "prifix-", "-suffix"));
        System.out.println(ids); // 输出prifix-a,b,c-suffix

    }

    /**
     * 年龄排序
     */
    static void sort(){
        Collections.sort(userDemoList, Comparator.comparing(UserDemo::getAge));
    }

    /**
     * 年龄求和
     */
    static void sum(){
        int amout = userDemoList.stream().map(UserDemo::getAge).reduce(Integer::sum).get();
        System.out.println(amout);
    }

    /**
     * 打印
     */
    static void print(){
        userDemoList.forEach(o -> System.out.println(o.getId() + o.getName() + o.getAge()));
    }

    public static void main(String[] args) {

        init();

        filter();
        // joinId();
        // sort();
        // sum();
        // getIdList();

        print();
    }
}
