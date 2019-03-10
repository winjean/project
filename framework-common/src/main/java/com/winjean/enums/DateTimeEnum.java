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
     * "yyyy-MM-dd"
     */
    date1("yyyy-MM-dd"),
    /**
     * "yyyy/MM/dd"
     */
    date2("yyyy/MM/dd"),
    /**
     * "yyyy.MM.dd"
     */
    date3("yyyy.MM.dd"),
    /**
     * "yyMMdd"
     */
    date4("yyyyMMdd"),
    /**
     * "yyyy年MM月dd日"
     */
    date5("yyyy年MM月dd日"),
    /**
     * "yyyy-MM-dd"
     */
    date6("yyyy-MM"),
    /**
     * "yyyy/MM/dd"
     */
    date7("yyyy/MM"),
    /**
     * "yyyy.MM.dd"
     */
    date8("yyyy.MM"),
    /**
     * "yyMMdd"
     */
    date9("yyyyMM"),
    /**
     * "yyyy年MM月dd日"
     */
    date10("yyyy年MM月"),
    /**
     * "yyyy-MM-dd"
     */
    date11("yyyy"),

    /**
     * "yyyy-MM-dd HH:mm:ss"
     */
    dateTime1("yyyy-MM-dd HH:mm:ss"),
    /**
     * "yyyy/MM/dd HH:mm:ss"
     */
    dateTime2("yyyy/MM/dd HH:mm:ss"),
    /**
     * "yyyy.MM.dd HH:mm:ss"
     */
    dateTime3("yyyy.MM.dd HH:mm:ss"),
    /**
     * "yyMMdd HH:mm:ss"
     */
    dateTime4("yyyyMMdd HH:mm:ss"),
    /**
     * "yyyy年MM月dd日 HH:mm:ss"
     */
    dateTime5("yyyy年MM月dd日 HH:mm:ss"),
    /**
     * "yyyy-MM-dd HH:mm"
     */
    dateTime6("yyyy-MM-dd HH:mm"),
    /**
     * "yyyy/MM/dd HH:mm"
     */
    dateTime7("yyyy/MM/dd HH:mm"),
    /**
     * "yyyy.MM.dd HH:mm"
     */
    dateTime8("yyyy.MM.dd HH:mm"),
    /**
     * "yyMMdd HH:mm"
     */
    dateTime9("yyyyMMdd HH:mm"),
    /**
     * "yyyy年MM月dd日 HH:mm"
     */
    dateTime10("yyyy年MM月dd日 HH:mm"),

    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    dateTime11("yyyy-MM-dd HH:mm:ss:SSS"),

    /**
     * "HH:mm:ss"
     */
    time1("HH:mm:ss"),

    /**
     * "HH:mm"
     */
    time2("HH:mm");

    private final String value;

    public String getValue() {
        return value;
    }

    DateTimeEnum(String value) {
        this.value = value;
    }
}
