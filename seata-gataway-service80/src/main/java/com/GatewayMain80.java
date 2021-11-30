package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author username
 * @date 2021/11/30 12:02
 * @description:TODO
 * @since 8
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain80 {
    public static void main(String[] args) {
            SpringApplication.run(GatewayMain80.class,args);
        }
}
