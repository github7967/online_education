package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author LiSong
 * @since 2022-09-18
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})  //让该注解扫描到引入依赖的配置类
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
