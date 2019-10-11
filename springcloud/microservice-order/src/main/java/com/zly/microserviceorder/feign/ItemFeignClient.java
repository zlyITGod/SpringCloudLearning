package com.zly.microserviceorder.feign;


import com.zly.microserviceorder.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "zly-item")//声明这是一个Feign客户端,并且指明服务id(商品微服务)
public interface ItemFeignClient {

    //这里定义了类似springMVC用法的方法,就可以进行RESTful的调用了
    @GetMapping(value = "/item/{id}")
    public Item queryItemById(@PathVariable("id") Long id);

    /**
     *  1.由于我们在入口@EnableFeignClients注解，Spring启动后会扫描标注了@FeignClient注解的接口，然后生成代理类
     *  2.我们在@FeignClient接口中指定了value，其实就是指定了在Eureka中的服务名称
     *  3.在FeignClient中的定义方法以及使用了SpringMVC的注解，Feign就会根据注解中的内容生成对应的URL，然后基于Ribbon的负载均衡去调用REST服务
     *  4.为什么使用的是SpringMVC的注解？
     *    其实，Feign是有自己的注解的，是因为Spring Cloud对Feign做了增强，兼容了SpringMVC的注解，使我们的学习成本更低,设置的默认的契约是SpringMVC契约。
     */
}
