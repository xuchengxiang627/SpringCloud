package com.xcx.cloud.controller;

import com.xcx.cloud.apis.PayFeignApi;
import com.xcx.cloud.entities.PayDTO;
import com.xcx.cloud.util.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign")
public class OrderController {

    // public static final String PAY_URL = "http://localhost:8001";
    // public static final String PAY_URL = "http://cloud-payment-service"; // 服务注册中心（consul）上的微服务名称

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        // return restTemplate.postForObject(PAY_URL + "/pay/add", payDTO, ResultData.class);
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return payFeignApi.getPayInfo(id);
    }

    @GetMapping(value = "/pay/getInfoByConsul")
    private String getInfoByConsul() {
        return payFeignApi.getInfoByConsul();
    }





}