package com.winjean.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/18 15:41
 * 修改人：Administrator
 * 修改时间：2018/10/18 15:41
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */

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
