package com.winjean.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.winjean.common.BaseEntity;
import lombok.Data;


@Data
public class UserEntity extends BaseEntity {

    @JSONField(name = "user_id")
    private String id;

    @JSONField(name = "user_name")
    private String name;

    private String birthdate;

    private String sex;
}
