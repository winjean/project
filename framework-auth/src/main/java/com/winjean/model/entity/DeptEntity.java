package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity(name = "t_department")
public class DeptEntity extends BaseEntity {

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门父级ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 树的深度数
     */
    private boolean leaf = true;

}
