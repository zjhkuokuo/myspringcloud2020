package com.atguigu.alibabaSeata.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 21:00
 */
public interface AccountService {

    /**
     *  扣减账户余额
     */
    void decrease(Long userId, BigDecimal money);
}
