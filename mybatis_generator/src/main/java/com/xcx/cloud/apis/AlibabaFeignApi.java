package com.xcx.cloud.apis;

import com.xcx.cloud.entities.Pay;
import com.xcx.cloud.entities.PayDTO;
import com.xcx.cloud.util.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelFallback.class)
public interface AlibabaFeignApi {

    @GetMapping("/pay/nacos/{id}")
    String getPayInfo(@PathVariable("id") Integer id);

}
