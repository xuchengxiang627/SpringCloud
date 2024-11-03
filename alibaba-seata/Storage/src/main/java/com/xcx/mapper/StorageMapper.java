package com.xcx.mapper;

import com.xcx.pojo.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 27163
* @description 针对表【t_storage】的数据库操作Mapper
* @createDate 2024-11-02 21:36:58
* @Entity com.xcx.pojo.Storage
*/
public interface StorageMapper extends BaseMapper<Storage> {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}




