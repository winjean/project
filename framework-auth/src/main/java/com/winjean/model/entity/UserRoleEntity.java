package com.winjean.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
}
