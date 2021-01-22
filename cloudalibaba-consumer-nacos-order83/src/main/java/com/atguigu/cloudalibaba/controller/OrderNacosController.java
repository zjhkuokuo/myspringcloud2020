package com.atguigu.cloudalibaba.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/22 22:57
 */
@RestController
@Slf4j
public class OrderNacosController {

    public static final String SERVER_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

//    引入配置文件中已经配置好的集群地址，可以实现代码配置的分离，但是不知道为何报错，配置也没起作用
//    @Value("${service-url.nacos-user-service}")
//    private String  serverURL;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return restTemplate.getForObject(SERVER_URL+"/payment/nacos/"+id,String.class);
    }
}
