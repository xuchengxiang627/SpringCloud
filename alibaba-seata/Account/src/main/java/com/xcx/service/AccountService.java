package com.xcx.service;

import com.xcx.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 27163
* @description 针对表【t_account】的数据库操作Service
* @createDate 2024-11-02 21:18:05
*/
public interface AccountService extends IService<Account> {

    void decrease(Long userId, Long money);

}
