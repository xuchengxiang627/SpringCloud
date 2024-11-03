package com.xcx.cloud.controller;

import com.xcx.cloud.entities.Pay;
import com.xcx.cloud.entities.PayDTO;
import com.xcx.cloud.service.PayService;
import com.xcx.cloud.util.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pay")
@Tag(name = "支付服务模块", description = "支付服务CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/add")
    @Operation(summary = "添加支付信息", description = "添加支付信息,json为参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        System.out.println("pay = " + pay.toString());
        int result = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + result);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除支付信息", description = "删除支付信息，id为参数")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int result = payService.delete(id);
        return ResultData.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新支付信息", description = "更新支付信息，json为参数")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int result = payService.update(pay);
        return ResultData.success("成功更新记录，返回值：" + result);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "根据id查询支付信息", description = "根据id查询支付信息，id为参数")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/getAll")
    @Operation(summary = "查询所有支付信息", description = "查询所有支付信息")
    public List<Pay> getAll() {
        return payService.getAll();
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/getInfoByConsul")
    public String getInfoByConsul(@Value("${xcx.info}") String info) {
        return "端口号：" + port + "，信息：" + info;
    }

}
