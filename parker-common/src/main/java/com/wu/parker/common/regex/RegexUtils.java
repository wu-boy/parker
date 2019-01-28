package com.wu.parker.common.regex;

import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author wusq
 * @date 2019/1/28
 */
public class RegexUtils {

    /**
     * 手机号
     */
    public static String CELLPHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    public static void main(String[] args) {
        boolean match = Pattern.matches(CELLPHONE, "13511112222");
        System.out.println(match);
    }
}
