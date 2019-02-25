package com.winjean.config.DataSource;

/**
 * 项目名称：
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/27 15:10
 * 修改人：winjean
 * 修改时间：2018/11/27 15:10
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal(){
        @Override
        protected String initialValue() {
            return DataSourceEnum.local.getValue();
        }
    };

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDatasource() {
        contextHolder.remove();
    }
}
