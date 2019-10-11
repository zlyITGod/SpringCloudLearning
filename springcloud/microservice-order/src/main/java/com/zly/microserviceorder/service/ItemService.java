package com.zly.microserviceorder.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zly.microserviceorder.feign.ItemFeignClient;
import com.zly.microserviceorder.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${microservice.item.url}")//http://localhost:8081/item/
    private String ItemUrl;

    /*public Item queryItemById(Long id) {
        return this.restTemplate.getForObject("http://127.0.0.1:8081/item/"
                + id, Item.class);
    }*/

    /*public Item queryItemById(Long id) {
        return this.restTemplate.getForObject(this.ItemUrl
                + id, Item.class);
    }*/

  /*  public Item queryItemById(Long id) {
        String serviceId = "zly-item-01";
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()) {
            return null;
        }
        //为了演示,这里只获取一个实例
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println("------Host是:"+serviceInstance.getHost());
        System.out.println("------Port是:"+serviceInstance.getPort());
        String url = serviceInstance.getHost()+":"+serviceInstance.getPort();
        return restTemplate.getForObject("http://"+url+"/item/"+id,Item.class);
    }*/

    @Autowired
    private ItemFeignClient itemFeignClient;

    // 开启负载均衡后的实现
    // 在执行请求前会经过org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor这个拦截器，
    // 并且通过org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient中，通过serverId查找服务地址，然后在去做真正的请求。
    /*@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById(Long id) {
        String serviceId = "zly-item";
        return restTemplate.getForObject("http://"+serviceId+"/item/"+id,Item.class);
    }*/

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById(Long id) {
        return itemFeignClient.queryItemById(id);
    }

    public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
        return new Item(id, "查询商品信息出错!", null, null, null);
    }


}
