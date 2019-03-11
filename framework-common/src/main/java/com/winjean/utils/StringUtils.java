package com.winjean.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class StringUtils {
    /**
     * 生成uuid，去除'-'，大写
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

    }

    /**
     * 以字符串,分隔成List
     *
     * @param str
     * @return
     */
    public static List<String> StringToList(String str) {
        return Arrays.asList(StringToArray(str));
    }

    /**
     * 以字符串,分隔成数组
     *
     * @param str
     * @return
     */
    public static String[] StringToArray(String str) {
        return str.split(",");
    }

    /**
     * 截取第一个出现的大写字母前的字符返回
     *
     * @param string
     * @return
     */
    public static String getStringByRegExp(String string) {
        String result = "";
        String regExp = "(.*?)[A-Z]+";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }


    /**
     * 生成随机数字和字母
     *
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {

        String val = "";
        SecureRandom random = new SecureRandom();
        //length为几位密码
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
