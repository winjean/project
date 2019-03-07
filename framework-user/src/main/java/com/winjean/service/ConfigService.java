package com.winjean.service;

import com.winjean.model.entity.ConfigEntity;

import java.util.List;

public interface ConfigService {


    List<ConfigEntity> searchConfigs();

    ConfigEntity searchConfig(ConfigEntity configEntity);

}
