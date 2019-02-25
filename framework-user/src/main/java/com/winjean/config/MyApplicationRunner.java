package com.winjean.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目名称：
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/11/9 17:16
 * 修改人：Administrator
 * 修改时间：2018/11/9 17:16
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("********** ApplicationRunner is invoked ************");
    }
}
