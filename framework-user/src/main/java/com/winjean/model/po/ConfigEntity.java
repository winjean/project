package com.winjean.model.po;

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
public class ConfigEntity extends BaseEntity {

    private String routeKey;

    private String interfaceName;

    private String voAdapterName;

    private String produceAdapterName;

    private String resultAdapterName;

    private String configInfo;

    private String note;
}
