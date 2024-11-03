package com.xcx.controller;

import com.xcx.pojo.Order;
import com.xcx.service.OrderService;
import com.xcx.utils.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/order/create")
    public ResultData create(@RequestBody Order order) {
        System.out.println("order = " + order);
        orderService.create(order);
        return ResultData.success(order);
    }
}