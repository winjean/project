package com.winjean.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_role_resource")
public class RoleResourceEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "resource_id")
    private int resourceId;
}
