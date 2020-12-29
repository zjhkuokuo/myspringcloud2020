package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/25 16:05
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix      //用于client客户端访问服务端的微服务时开启服务降级
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
