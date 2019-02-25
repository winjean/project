package com.winjean.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.winjean.common.BaseEntity;
import lombok.Data;

/**
 * 项目名称：
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/18 14:57
 * 修改人：Administrator
 * 修改时间：2018/10/18 14:57
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */

@Data
public class UserEntity extends BaseEntity {

    @JSONField(name = "userId")
    private String user_id;

    @JSONField(name = "userName")
    private String user_name;

    private String birthdate;

    private String sex;
}
