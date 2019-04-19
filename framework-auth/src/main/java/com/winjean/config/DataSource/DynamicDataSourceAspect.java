package com.winjean.config.DataSource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

//@Aspect
//@Component
//@Order(10)
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
