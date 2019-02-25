package com.winjean.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/11/9 17:12
 * 修改人：Administrator
 * 修改时间：2018/11/9 17:12
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
@Component
@Slf4j
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("********* ContextRefreshedEvent executed ***********");
    }
}
