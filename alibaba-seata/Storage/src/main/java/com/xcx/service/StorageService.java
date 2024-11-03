package com.xcx.service;

import com.xcx.pojo.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 27163
* @description 针对表【t_storage】的数据库操作Service
* @createDate 2024-11-02 21:36:58
*/
public interface StorageService extends IService<Storage> {

    void decrease(Long productId, Integer count);

}
