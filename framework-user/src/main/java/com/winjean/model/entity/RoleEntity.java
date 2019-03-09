package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class RoleEntity extends BaseEntity {

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
}
