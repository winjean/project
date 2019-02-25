package com.winjean.utils;

import java.util.Random;


public class CodeUtils {

    /**
     * 产生随机的六位数
     */
    public static String getSix() {
        Random rad = new Random();
        String result = rad.nextInt(1000000) + "";
        if (result.length() != 6) {
            return getSix();
        }
        return result;
    }

    /**
     * hash码生成唯一标识
     *
     * @param code
     * @param phone
     * @param uid
     * @param formid
     * @return
     */
    public static int hashCode(String code, String phone, String uid, String formid) {
        // TODO Auto-generated method stub
        int hash = 1;
        hash = hash * 31 + code.hashCode();
        hash = hash * 31
                + (phone == null ? 0 : phone.hashCode());
        hash = hash * 31
                + (uid == null ? 0 : uid.hashCode());
        hash = hash * 31
                + (formid == null ? 0 : formid.hashCode());
        return hash;
    }
}