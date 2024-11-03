package com.xcx.cloud.apis;

import com.xcx.cloud.entities.Pay;
import com.xcx.cloud.entities.PayDTO;
import com.xcx.cloud.util.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("cloud-payment-service") // 与 consul 微服务名称对应
public interface PayFeignApi {
    // 与 cloud-provider-payment8001 中定义的controller对应

    @PostMapping("/pay/add")
    @Operation(summary = "添加支付信息", description = "添加支付信息,json为参数")
    ResultData<String> addPay(@RequestBody PayDTO pay);

    @GetMapping("/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);


    @GetMapping("/pay/getInfoByConsul")
    String getInfoByConsul();

    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);


    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
