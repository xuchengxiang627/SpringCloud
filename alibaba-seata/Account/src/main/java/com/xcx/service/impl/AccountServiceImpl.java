package com.xcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcx.pojo.Account;
import com.xcx.service.AccountService;
import com.xcx.mapper.AccountMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @author 27163
* @description 针对表【t_account】的数据库操作Service实现
* @createDate 2024-11-02 21:18:05
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

    @Resource
    AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, Long money) {
        System.out.println("------->account-service中扣减账户余额开始");

        accountMapper.decrease(userId,money);

        myTimeOut();
        // int age = 10/0;
        System.out.println("------->account-service中扣减账户余额结束");
    }

    /**
     * 模拟超时异常，全局事务回滚
     */
    private static void myTimeOut()
    {
        try { TimeUnit.SECONDS.sleep(65); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}




