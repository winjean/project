package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;


@Data
public class RoleEntity extends BaseEntity {

    private String id;

    private String name;

    /**
     * 是否可用
     */
    private byte state;
}
