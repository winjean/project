package com.winjean.enums;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/25 19:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public enum DateTimeEnum {
    /**
     * "yyyy-MM-dd HH:mm:ss"
     */
    type1("yyyy-MM-dd HH:mm:ss"),
    /**
     * "yyyy/MM/dd HH:mm:ss"
     */
    type2("yyyy/MM/dd HH:mm:ss"),
    /**
     * "yyyy.MM.dd HH:mm:ss"
     */
    type3("yyyy.MM.dd HH:mm:ss"),
    /**
     * "yyMMdd HH:mm:ss"
     */
    type4("yyyyMMdd HH:mm:ss"),
    /**
     * "yyyy年MM月dd日 HH:mm:ss"
     */
    type5("yyyy年MM月dd日 HH:mm:ss"),
    /**
     * "yyyy-MM-dd HH:mm"
     */
    type6("yyyy-MM-dd HH:mm"),
    /**
     * "yyyy/MM/dd HH:mm"
     */
    type7("yyyy/MM/dd HH:mm"),
    /**
     * "yyyy.MM.dd HH:mm"
     */
    type8("yyyy.MM.dd HH:mm"),
    /**
     * "yyMMdd HH:mm"
     */
    type9("yyyyMMdd HH:mm"),
    /**
     * "yyyy年MM月dd日 HH:mm"
     */
    type10("yyyy年MM月dd日 HH:mm");

    private final String value;

    public String getValue() {
        return value;
    }

    DateTimeEnum(String value) {
        this.value = value;
    }
}
