package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author username
 * @date 2021/11/28 11:58
 * @description:TODO
 * @since 8
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigMain3344 {
    public static void main(String[] args) {
            SpringApplication.run(ConfigMain3344.class,args);
        }
}
