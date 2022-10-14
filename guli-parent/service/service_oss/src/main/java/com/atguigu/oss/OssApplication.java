package com.atguigu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author LiSong
 * @since 2022-09-23
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //解除自动加载DataSourceAutoConfiguration
@ComponentScan(basePackages = {"com.atguigu"})
@EnableDiscoveryClient  //向nacos中注册该服务
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
