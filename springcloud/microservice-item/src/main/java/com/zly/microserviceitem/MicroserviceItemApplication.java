package com.zly.microserviceitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//声明Eureka客户端
@SpringBootApplication
public class MicroserviceItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceItemApplication.class, args);
    }

}
