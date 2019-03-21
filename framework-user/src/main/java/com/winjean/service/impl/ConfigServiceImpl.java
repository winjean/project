package com.winjean.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winjean.config.DataSource.DataSourceConstant;
import com.winjean.config.DataSource.TargetDataSource;
import com.winjean.mapper.ConfigMapper;
import com.winjean.model.entity.ConfigEntity;
import com.winjean.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService{

    @Resource
    private ConfigMapper configMapper;

    /**
     * readOnly=true
     * 一次执行单条查询语句，没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性
     * 一次执行多条查询语句，多条查询SQL必须保证整体的读一致性(在前条SQL查询之后，
     * 后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态)
     **/
    @Override
    @TargetDataSource(DataSourceConstant.REMOTE)
    @Transactional(readOnly = true)
    public List<ConfigEntity> searchConfigs() {
        log.info("");
        PageHelper.startPage(2,2);
        Page<ConfigEntity> configs = configMapper.searchConfigs();
        return configs.getResult();
    }

    @Override
    @TargetDataSource(DataSourceConstant.REMOTE)
    @Transactional(readOnly = true)
    public ConfigEntity searchConfig(ConfigEntity config) {
        return configMapper.searchConfig(config);
    }
}
