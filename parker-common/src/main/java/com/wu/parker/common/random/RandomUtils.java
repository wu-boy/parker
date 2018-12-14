package com.wu.parker.common.random;

import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 * @author: wusq
 * @date: 2018/11/8
 */
public class RandomUtils {

    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    public static String uuidWithoutBar(){
        return uuid().replace("-", "");
    }

    /**
     * 生成6位数字随机验证码
     * @return
     */
    public static String verificationCode(){
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder(6);
        for(int i=0; i<6; i++){
            char c = numbers.charAt(new Random().nextInt(numbers.length()));
            sb.append(c);
        }
        return sb.toString();
    }
}
