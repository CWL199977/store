package com.cy.store;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication

//在启动类上添加@MapperScan注解，指定扫描的mapper接口所在的包路径，
// 这样就可以在启动类上直接使用@MapperScan注解了
@MapperScan("com.cy.store.mapper")

public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
        log.info("启动成功");
    }

}
