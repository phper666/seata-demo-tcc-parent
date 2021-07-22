package com.phper666.seata.demo.tcc.storage.provider;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.phper666.seata.demo.tcc.storage.provider.mapper")
public class StorageProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageProviderApplication.class, args);
    }

}

