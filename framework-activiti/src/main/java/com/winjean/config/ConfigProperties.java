package com.winjean.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/13 19:31
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Component
@ConfigurationProperties(prefix = "config.winjean")
@Data
public class ConfigProperties {

    private String name = "winjean";

    private int age = 40;
}
