package com.winjean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目名称：
 * 类名称：
 * 类描述：
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */
@SpringBootApplication
@ComponentScan({"com.winjean.*"})
@MapperScan("com.winjean.mapper.*")

public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
