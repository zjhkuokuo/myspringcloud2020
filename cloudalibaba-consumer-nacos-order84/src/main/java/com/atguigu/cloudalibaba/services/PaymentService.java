package com.atguigu.cloudalibaba.services;

import com.atguigu.cloudalibaba.services.Impl.PaymentFallbackService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/31 22:52
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    //对应9003/9004服务提供者的被消费者调用的方法
    //当访问9003/9004服务提供者的这个地址提供的服务，而该服务地址出问题时，会执行该方法的全局服务熔断
    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> testOpenfeign(@PathVariable("id")Long id);

}
