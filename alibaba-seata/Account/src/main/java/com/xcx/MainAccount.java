package com.xcx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xcx.mapper")
@EnableFeignClients
public class MainAccount {
    public static void main(String[] args) {
        SpringApplication.run(MainAccount.class, args);
    }
}
