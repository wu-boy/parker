package com.wu.parker.common.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 * @author: wusq
 * @date: 2018/11/26
 */
public class EncryptUtils {

    /**
     * 默认散列次数
     */
    public static final Integer HASH_ITERATIONS = 1;

    /**
     * Shiro的MD5加密
     * @param password 密码
     * @param salt 盐
     * @return
     */
    /*public static String shiroMd5(String password, String salt){
        String algorithmName = "md5";
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, byteSalt, HASH_ITERATIONS);
        return simpleHash.toHex();
    }*/

    /**
     * Java的MD5加密
     * @param password
     * @return
     */
    public static String md5(String password){
        String result = null;
        byte[] bytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对字符串进行加密
            md.update(password.getBytes());
            // 获得加密后的数据
            bytes = md.digest();

            // 将加密后的数据转换为16进制数字
            result = new BigInteger(1, bytes).toString(16);// 16进制数字
            // 如果生成数字未满32位，需要前面补0
            for (int i = 0; i < 32 - result.length(); i++) {
                result = "0" + result;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        return result;
    }

    public static void main(String[] args) {
        /*String password = md5("admin" + "admin");
        System.out.println(password);
        String newPassword = md5(password + "f487629c56eb4d3096800c05efb621f5");
        System.out.println(newPassword);*/

        String password = md5("admin" + "12345678");
        System.out.println(password);
        String newPassword = md5(password + "0c3c948d968a4a40b8473557d2889aa2");
        System.out.println(newPassword);
    }
}
