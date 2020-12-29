package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/25 14:33
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker       //用于server端服务降级
public class PaymentHystrixMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
