package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/8 15:29
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@Entity(name = "t_resource")
public class ResourceEntity extends BaseEntity {

    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="resource_id")
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 菜单名称，按钮名称,目录名称
     */
    private String name;

    /**
     * 是否叶子节点
     */
    private boolean leaf = true;

    /**
     * 样式标签
     */
    private String css;

    /**
     * 图标
     */
    private String ico;

    /**
     * 路由地址
     */
    private String url;

    /**
     * 类型：1 目录，2 菜单，3按钮
     */
    private int type;

    /**
     * 父类ID
     */
    @Column(name="parent_id")
    private Long parentId;

    /**
     * 排序序号
     */
    private int sort;

    /**
     * 树的深度数
     */
    private int level;

    /**
     * 是否可见
     */
    private boolean visible;

    /**
     * 是否可用
     */
    private boolean enable;
}
