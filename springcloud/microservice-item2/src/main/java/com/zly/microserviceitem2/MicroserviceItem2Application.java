package com.zly.microserviceitem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//声明Eureka客户端
@SpringBootApplication
public class MicroserviceItem2Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceItem2Application.class, args);
    }

}
