package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/24 15:23
 */
@Component
@FeignClient("cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id")Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();

}
