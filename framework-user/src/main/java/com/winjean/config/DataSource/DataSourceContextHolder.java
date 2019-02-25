package com.winjean.config.DataSource;


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
