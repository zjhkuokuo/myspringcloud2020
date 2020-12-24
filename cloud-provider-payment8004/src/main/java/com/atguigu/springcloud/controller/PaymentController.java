package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/21 10:19
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping("/payment/zookeeper")
    public String create(){
        return "springcloud with zookeeper:" + serverport +"\t"+ UUID.randomUUID().toString();
    }
}
