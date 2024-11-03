package com.xcx.cloud.controller;

import com.xcx.cloud.apis.AlibabaFeignApi;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    public String PAY_URL; // 服务注册中心（nacos）上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private AlibabaFeignApi alibabaFeignApi;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        // return restTemplate.getForObject(PAY_URL + "/pay/nacos/" + id, String.class);
        return alibabaFeignApi.getPayInfo(id);
    }



}