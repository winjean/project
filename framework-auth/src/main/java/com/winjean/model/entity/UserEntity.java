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
    private String id;

    @Column(name = "user_name")
    private String name;

    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    @JsonFormat(/*locale="zh", timezone="GMT+8",*/ pattern="yyyy-MM-dd")
    private Date birthday;

    private String telephone;

    private String sex;

    private String email;

    private byte state = 1;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
