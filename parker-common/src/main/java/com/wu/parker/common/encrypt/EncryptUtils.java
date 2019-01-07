package com.wu.parker.common.encrypt;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 * @author: wusq
 * @date: 2019/1/7
 */
public class EncryptUtils {

    /**
     * 默认加密次数
     */
    public static final Integer DEFAULT_ITERATIONS = 1;

    /**
     * Shiro的MD5加密，加密方式是对字符串salt+password进行加密
     * @param salt 盐
     * @param password 密码
     * @return
     */
    public static String shiroMd5(String salt, String password){
        String algorithmName = "MD5";
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, byteSalt, DEFAULT_ITERATIONS);
        return simpleHash.toHex();
    }

    /**
     * SHA-256加密
     * @param password
     * @return
     */
    public static String shiroSha256(String password){
        Sha256Hash sha256Hash = new Sha256Hash(password);
        return sha256Hash.toHex();
    }

    /**
     * Java的MD5加密，加密方式是对字符串salt+password进行加密
     * @param salt 盐
     * @param password
     * @return
     */
    public static String md5(String salt, String password){
        String result = null;
        byte[] bytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对字符串进行加密
            md.update((salt + password).getBytes());
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

        /*// MD5加密测试
        String password1 = shiroMd5("admin", "12345678");
        System.out.println(password1);

        String password2 = md5("admin", "12345678");
        System.out.println(password2);

        // 两者加密结果相同
        System.out.println(password1.equals(password2));*/


        // SHA-256加密测试
        System.out.println(shiroSha256("12345678"));
    }
}
