package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="role_id")
    private String id;

    @Column(name = "role_name")
    private String roleName;

    /**
     * 是否可用
     */
    private byte state;

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
