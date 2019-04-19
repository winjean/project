package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_role")
public class RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="role_id")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String name;

    /**
     * 是否可用
     */
    private boolean enable = true;
}
