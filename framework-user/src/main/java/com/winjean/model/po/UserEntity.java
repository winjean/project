package com.winjean.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.winjean.common.BaseEntity;
import lombok.Data;


@Data
public class UserEntity extends BaseEntity {

    @JSONField(name = "userId")
    private String user_id;

    @JSONField(name = "userName")
    private String user_name;

    private String birthdate;

    private String sex;
}
