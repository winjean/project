package com.winjean.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
}
