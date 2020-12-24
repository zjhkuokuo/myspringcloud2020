package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/21 17:05
 */
@RestController
@Slf4j
public class OrderConsulController {

    private static final String CONSUL_URL = "http://consul-provider-payment8006";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String getPayment(){
        return restTemplate.getForObject(CONSUL_URL + "/payment/consul" ,String.class);
    }




}
