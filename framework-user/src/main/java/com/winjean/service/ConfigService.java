package com.winjean.service;

import com.winjean.model.po.ConfigEntity;

import java.util.List;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/18 14:54
 * 修改人：Administrator
 * 修改时间：2018/10/18 14:54
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
public interface ConfigService {


    List<ConfigEntity> searchConfigs();

    ConfigEntity searchConfig(ConfigEntity configEntity);

}
