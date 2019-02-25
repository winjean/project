package com.winjean.config.DataSource;

import java.lang.annotation.*;

/**
 * 项目名称：
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/28 11:34
 * 修改人：winjean
 * 修改时间：2018/11/28 11:34
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
