package com.winjean;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.winjean.*"})
@MapperScan("com.winjean.mapper.*")
@Slf4j
public class UserApplication {
    public static void main(String[] args) {
        log.info("-------main log------------");
        SpringApplication.run(UserApplication.class,args);
    }
}
