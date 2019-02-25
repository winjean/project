package com.winjean.utils;

import org.apache.http.util.Asserts;

import java.security.MessageDigest;


public class EncryptUtils {


    // md5加密
    public static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }

    // sha加密
    public static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }

    /**
     * md5或者sha-1加密
     *
     * @param inputText     要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return
     */
    private static String encrypt(String inputText, String algorithmName) {
        Asserts.notBlank(inputText, "algorithm name");
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            return hex(s);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回十六进制字符串
     *
     * @param arr 需要加密字节数组
     * @return
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

}
