package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/8 15:29
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
public class ResourceEntity extends BaseEntity {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 菜单名称，按钮名称,目录名称
     */
    private String name;

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
    private String parentId;

    /**
     * 排序序号
     */
    private int sort;

    /**
     * 树的深度数
     */
    private int level;

    /**
     * 是否可用
     */
    private byte state;

}
