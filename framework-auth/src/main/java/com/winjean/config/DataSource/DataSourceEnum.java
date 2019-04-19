package com.winjean.config.DataSource;


public enum DataSourceEnum {
    local(DataSourceConstant.LOCAL,"local database"),
    remote(DataSourceConstant.REMOTE,"remote database");

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private DataSourceEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
