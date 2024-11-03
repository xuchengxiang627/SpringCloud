package com.xcx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcx.apis.AccountFeignApi;
import com.xcx.apis.StorageFeignApi;
import com.xcx.pojo.Order;
import com.xcx.service.OrderService;
import com.xcx.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 27163
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2024-11-02 19:29:06
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource//订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;
    @Resource//订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;


    @Override
    @GlobalTransactional(name = "create-order",rollbackFor = Exception.class) //AT
    //@GlobalTransactional @Transactional(rollbackFor = Exception.class) //XA
    public void create(Order order) {

        //xid检查
        String xid = RootContext.getXID();

        //1. 新建订单
        System.out.println("==================>开始新建订单"+"\t"+"xid_order:" +xid);
        //订单状态status：0：创建中；1：已完结
        order.setStatus(0);
        int result = orderMapper.insert(order);

        //插入订单成功后获得插入mysql的实体对象
        Order orderFromDB = null;
        if(result > 0) {
            orderFromDB = orderMapper.selectOne(new QueryWrapper<>(order));
            //orderFromDB = orderMapper.selectByPrimaryKey(order.getId());
            System.out.println("-------> 新建订单成功，orderFromDB info: " + orderFromDB);
            System.out.println();
            //2. 扣减库存
            System.out.println("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            System.out.println("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            //3. 扣减账号余额
            System.out.println("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            System.out.println("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            //4. 修改订单状态
            //订单状态status：0：创建中；1：已完结
            System.out.println("-------> 修改订单状态");
            // orderFromDB.setStatus(1);
            LambdaUpdateWrapper<Order> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Order::getId, orderFromDB.getId())
                    .set(Order::getStatus, 1);
            int updateResult = orderMapper.update(updateWrapper);

            System.out.println("-------> 修改订单状态完成"+"\t" + updateResult);
            System.out.println("-------> orderFromDB info: " + orderFromDB);
        }
        System.out.println();
        System.out.println("==================>结束新建订单"+"\t"+"xid_order:" +xid);

    }
}




