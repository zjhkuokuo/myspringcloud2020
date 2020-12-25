package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/25 16:07
 */
@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        return paymentService.paymentInfoOk(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_TimeOut(id);
    }
}
