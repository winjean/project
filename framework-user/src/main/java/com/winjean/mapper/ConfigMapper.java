package com.winjean.mapper;

import com.github.pagehelper.Page;
import com.winjean.model.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConfigMapper {

    @Select("select * from t_interface_config")
    Page<ConfigEntity> searchConfigs();

    @Select("select * from t_interface_config where routeKey=#{routeKey}")
    ConfigEntity searchConfig(ConfigEntity configEntity);
}
