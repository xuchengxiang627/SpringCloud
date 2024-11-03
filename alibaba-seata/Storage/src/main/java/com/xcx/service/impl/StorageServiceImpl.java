package com.xcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcx.pojo.Storage;
import com.xcx.service.StorageService;
import com.xcx.mapper.StorageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 27163
* @description 针对表【t_storage】的数据库操作Service实现
* @createDate 2024-11-02 21:36:58
*/
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        System.out.println("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId,count);
        System.out.println("------->storage-service中扣减库存结束");
    }

}




