package com.winjean.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;


public final class NumberUtils {

    /**
     * 格式化数字类型成字符串类型
     *
     * @param dou 待格式化的数字
     * @param reg 格式
     * @return 返回格式化后的字符串
     */
    public static String format(Double dou, String reg) {
        Double db = dou;
        if (db == null) {
            db = 0.0;
        }
        String format = reg;
        if (StringUtils.isEmpty(format)) {
            format = "0.00";
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(db);
    }

    /**
     * 转换字符串为double数字类型
     *
     * @param num 待转换字符串
     * @return 转换结果
     */
    public static Double parseDouble(String num) {
        String result = num;
        if (StringUtils.isEmpty(result)) {
            result = "0";
        }
        return org.apache.commons.lang3.math.NumberUtils.createDouble(result);
    }

    /**
     * 转换字符串为Bigdecimal字类型
     *
     * @param num 待转换字符串
     * @return 转换结果
     */
    public static BigDecimal parseBigDecimal(String num) {
        String result = num;
        if (StringUtils.isEmpty(result)) {
            result = "0";
        }
        return org.apache.commons.lang3.math.NumberUtils.createBigDecimal(result);
    }

    /**
     * 转换字符串为Integer字类型
     *
     * @param num 待转换字符串
     * @return 转换结果
     */
    public static Integer parseInteger(String num) {
        String result = num;
        if (StringUtils.isEmpty(result)) {
            result = "0";
        }
        return org.apache.commons.lang3.math.NumberUtils.createInteger(result);
    }

    /**
     * 转换字符串为float字类型
     *
     * @param num 待转换字符串
     * @return 转换结果
     */
    public static Float parseFloat(String num) {
        String result = num;
        if (StringUtils.isEmpty(result)) {
            result = "0";
        }
        return org.apache.commons.lang3.math.NumberUtils.createFloat(result);
    }

    /**
     * 左补字符0
     *
     * @param str
     * @param length
     * @return
     */
    public static String fill0Left(String str, int length) {
        if (str == null) {
            str = "";
        }
        int len = length - str.length();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                str = "0" + str;
            }
        }
        return str;
    }

    /**
     * 生成随机位随机数字
     *
     * @param len 随机数的位数
     * @return
     */
    public static String genRandomNum(int len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的长度
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer num = new StringBuffer("");
        Random r = new Random();
        while (count < len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                num.append(str[i]);
                count++;
            }
        }
        return num.toString();
    }
}
