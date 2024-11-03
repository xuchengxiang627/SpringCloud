package com.xcx.service;

import com.xcx.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 27163
* @description 针对表【t_order】的数据库操作Service
* @createDate 2024-11-02 19:29:06
*/
public interface OrderService extends IService<Order> {

    void create(Order order);
}
