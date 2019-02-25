package com.winjean.config.DataSource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目名称：
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/28 11:35
 * 修改人：winjean
 * 修改时间：2018/11/28 11:35
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
@Aspect
@Component
@Order(10)
public class DynamicDataSourceAspect {

    @Before("@annotation(targetDataSource))")
    public void switchDataSource(TargetDataSource targetDataSource) {
        // 切换数据源
        DataSourceContextHolder.setDataSource(targetDataSource.value());
    }

    @After("@annotation(targetDataSource))")
    public void restoreDataSource(TargetDataSource targetDataSource) {
        // 将数据源置为默认数据源
        DataSourceContextHolder.clearDatasource();
    }
}
