package com.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author username
 * @date 2021/11/28 11:17
 * @description:TODO
 * @since 8
 */
@MapperScan("com.wang.dao")
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class StorageMain8001 {
    public static void main(String[] args) {
            SpringApplication.run(StorageMain8001.class,args);
        }
}
