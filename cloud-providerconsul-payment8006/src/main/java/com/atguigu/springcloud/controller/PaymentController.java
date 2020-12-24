package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/21 16:48
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping("/payment/consul")
    public String getPayment(){
        return "springcloud with consul: "+ serverport + "\t" + UUID.randomUUID().toString();
    }
}
