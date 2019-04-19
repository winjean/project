package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity(name = "t_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 出生日期
     */
    @JsonFormat(/*locale="zh", timezone="GMT+8",*/ pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否可用
     */
    private boolean enable = true;
}
