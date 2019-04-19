package com.winjean.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_role_resource")
public class RoleResourceEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "resource_id")
    private Long resourceId;
}
