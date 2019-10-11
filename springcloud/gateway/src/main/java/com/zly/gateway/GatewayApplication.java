package com.zly.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    //使用正则表达式指定路由规则
    /*@Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        //调用PatternServiceRouteMapper(String servicePattern, String routePattern)
        //servicePattern指定微服务正则  routePattern指定路由正则
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<vwesion>v.+$)",
                "${version}/${name}"
        );
    }
    通过这段代码可以将
    microservice-provider-user-v1这个微服务 ,映射到
    /v1/ microservice-provider-user/**
    */

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
