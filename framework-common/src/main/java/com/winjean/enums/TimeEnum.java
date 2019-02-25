package com.winjean.enums;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/25 19:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public enum TimeEnum {

    /**
     * "HH:mm:ss"
     */
    type1("HH:mm:ss"),

    /**
     * "HH:mm"
     */
    type2("HH:mm");

    private final String value;

    public String getValue() {
        return value;
    }

    TimeEnum(String value) {
        this.value = value;
    }
}
