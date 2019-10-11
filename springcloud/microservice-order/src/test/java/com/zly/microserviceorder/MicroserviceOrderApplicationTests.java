package com.zly.microserviceorder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(MicroserviceOrderApplication.class)
public class MicroserviceOrderApplicationTests {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void test(){
        String serviceId = "zly-item-01";
        for (int i = 0; i < 100; i++) {
            ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
            System.out.println("第"+(i+1)+"次：" + serviceInstance.getHost() + ": " + serviceInstance.getPort());
        }
    }
    //测试结果 item1和item2是轮询的
    //如果加上配置zly-item-01.ribbon.NFLoadBalancerRuleClassName:com.netflix.loadbalancer.RandomRule
    //则变为随机

    @Test
    public void contextLoads() {
    }

}
