package com.xcx.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xcx.cloud.apis.PayFeignSentinelFallback;
import com.xcx.cloud.util.ResultData;
import com.xcx.cloud.util.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/pay/nacos/{id}")
    @SentinelResource(value = "getPayInfo", blockHandler = "blockHandler")
    public String getPayInfo(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        return "AlibabaCloud nacos config, serverPort: " + serverPort + "\t id: " + id;
    }

    public String blockHandler(@PathVariable("id") Integer id, BlockException exception)
    {
        return "getPayByOrderNo服务不可用，" + "触发sentinel流控配置规则";
    }
}
