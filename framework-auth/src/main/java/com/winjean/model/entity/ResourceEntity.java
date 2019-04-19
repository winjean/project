package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/8 15:29
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@Entity(name = "t_resource")
public class ResourceEntity {

    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    private boolean enable;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
