package com.xcx.cloud.controller;

import com.xcx.cloud.entities.PayDTO;
import com.xcx.cloud.util.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    // public static final String PAY_URL = "http://localhost:8001";
    public static final String PAY_URL = "http://cloud-payment-service"; // 服务注册中心（consul）上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAY_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/pay/delete/{id}")
    public void delOrder(@PathVariable String id) {
        restTemplate.delete(PAY_URL + "/pay/delete" + id, id);
    }

    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAY_URL + "/pay/get/"+id, ResultData.class, id);
    }

    @GetMapping(value = "/pay/getInfoByConsul")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PAY_URL + "/pay/getInfoByConsul", String.class);
    }




}