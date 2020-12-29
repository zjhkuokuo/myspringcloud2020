package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 *  通配的服务降级类
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/28 16:07
 */
@Component
public class PaymentFallbackService implements PaymentService {


    @Override
    public String paymentInfoOk(Integer id) {
        return "Hystrix服务降级通配方法，对应接口的paymentInfoOk()";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "Hystrix服务降级通配方法，对应接口的paymentInfo_TimeOut()";
    }
}
