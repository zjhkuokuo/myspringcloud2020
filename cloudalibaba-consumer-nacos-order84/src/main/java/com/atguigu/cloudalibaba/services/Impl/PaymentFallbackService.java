package com.atguigu.cloudalibaba.services.Impl;

import com.atguigu.cloudalibaba.services.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/31 22:53
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> testOpenfeign(Long id) {

        return new CommonResult<>(600,"整合Openfeign,定义全局的熔断降级",new Payment(id,"errorSerial"));
    }
}
