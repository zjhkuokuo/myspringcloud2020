package com.atguigu.alibabaSeata.dao;

import com.atguigu.alibabaSeata.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/3 22:04
 */
@Mapper
public interface OrderDao {

    //新建订单
    void create(Order order);

    //更改订单状态,从 0 改为 1
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
