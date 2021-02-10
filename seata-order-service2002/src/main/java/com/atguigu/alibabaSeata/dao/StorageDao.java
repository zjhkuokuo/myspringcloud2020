package com.atguigu.alibabaSeata.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 22:39
 */
@Mapper
public interface StorageDao {

    //扣减库存信息
    void decrease(@Param("productId")Long productId, @Param("count")Integer count);
}
