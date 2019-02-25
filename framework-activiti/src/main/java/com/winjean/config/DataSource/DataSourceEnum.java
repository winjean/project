package com.winjean.config.DataSource;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/27 15:12
 * 修改人：winjean
 * 修改时间：2018/11/27 15:12
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
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
