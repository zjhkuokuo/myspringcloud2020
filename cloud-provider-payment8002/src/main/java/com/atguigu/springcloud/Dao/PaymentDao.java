package com.atguigu.springcloud.Dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @projectName cloud2020
 * @author ZENG JIAN HUI
 * @createTime 2020/12/9 16:25
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
