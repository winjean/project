package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;


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
