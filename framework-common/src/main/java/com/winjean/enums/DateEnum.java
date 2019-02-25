package com.winjean.enums;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/25 19:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public enum DateEnum {

    /**
     * "yyyy-MM-dd"
     */
    type1("yyyy-MM-dd"),
    /**
     * "yyyy/MM/dd"
     */
    type2("yyyy/MM/dd"),
    /**
     * "yyyy.MM.dd"
     */
    type3("yyyy.MM.dd"),
    /**
     * "yyMMdd"
     */
    type4("yyyyMMdd"),
    /**
     * "yyyy年MM月dd日"
     */
    type5("yyyy年MM月dd日"),
    /**
     * "yyyy-MM-dd"
     */
    type6("yyyy-MM"),
    /**
     * "yyyy/MM/dd"
     */
    type7("yyyy/MM"),
    /**
     * "yyyy.MM.dd"
     */
    type8("yyyy.MM"),
    /**
     * "yyMMdd"
     */
    type9("yyyyMM"),
    /**
     * "yyyy年MM月dd日"
     */
    type10("yyyy年MM月"),
    /**
     * "yyyy-MM-dd"
     */
    type11("yyyy");

    private final String value;

    public String getValue() {
        return value;
    }

    DateEnum(String value) {
        this.value = value;
    }
}
