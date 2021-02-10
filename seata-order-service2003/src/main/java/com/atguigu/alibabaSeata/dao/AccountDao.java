package com.atguigu.alibabaSeata.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 20:55
 */
@Mapper
public interface AccountDao {

    /**
     *  扣减账户余额
     */
    void decrease(@Param("userId")Long userId,@Param("money") BigDecimal money);
}
