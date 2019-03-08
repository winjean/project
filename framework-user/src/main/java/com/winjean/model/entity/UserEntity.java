package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;


@Data
public class UserEntity extends BaseEntity {

    private String id;

    private String name;

    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    private String birthday;

    private String telephone;

    private String sex;

    private String email;

    private byte state;
}
