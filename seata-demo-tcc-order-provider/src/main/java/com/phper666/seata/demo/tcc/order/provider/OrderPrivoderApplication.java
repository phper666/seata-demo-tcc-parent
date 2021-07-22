package com.phper666.seata.demo.tcc.order.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.phper666.seata.demo.tcc.order.provider.mapper")
public class OrderPrivoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPrivoderApplication.class, args);
    }

}

