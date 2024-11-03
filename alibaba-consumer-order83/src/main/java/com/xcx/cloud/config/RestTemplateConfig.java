package com.xcx.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// @LoadBalancerClient(
//         //下面的value值大小写一定要和consul里面的名字一样，必须一样      修改负载均衡算法
//         value = "cloud-payment-service",configuration = RestTemplateConfig.class)
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }

    // // 自定义负载均衡算法,将轮询切换为随机
    // @Bean
    // ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
    //     String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
    //     return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    // }
}
