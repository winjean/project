package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private String id;

    @Column(name = "user_name")
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

    private byte state = 1;
}
