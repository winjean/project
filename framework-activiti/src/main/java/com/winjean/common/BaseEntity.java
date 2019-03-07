package com.winjean.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



@Data
public class BaseEntity implements Serializable {

    @JSONField(name = "createUser")
    private String create_user;

    @JSONField(name = "createTime")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyyMMdd HH:mm:ss")
    private Date create_time;

    @JSONField(name = "updateUser")
    private String update_user;

    @JSONField(name = "updateTime")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyyMMdd HH:mm:ss")
    private Date update_time;
}
