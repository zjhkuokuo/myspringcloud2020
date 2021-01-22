package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/21 14:25
 */
@RestController
@Slf4j
public class OrderzkController {

    private  static final String INVOME_URL = "http://cloud-provider-payment8004";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zookeeper")
    public String getPayment(){
        String forObject = restTemplate.getForObject(INVOME_URL + "/payment/zookeeper", String.class);
        return forObject;
    }


}
