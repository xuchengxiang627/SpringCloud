package com.xcx.mapper;

import com.xcx.pojo.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 27163
* @description 针对表【t_account】的数据库操作Mapper
* @createDate 2024-11-02 21:18:05
* @Entity com.xcx.pojo.Account
*/
public interface AccountMapper extends BaseMapper<Account> {

    void decrease(@Param("userId") Long userId, @Param("money") Long money);

}




