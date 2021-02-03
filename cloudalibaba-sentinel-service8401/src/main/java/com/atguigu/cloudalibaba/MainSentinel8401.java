package com.atguigu.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/27 10:57
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MainSentinel8401 {
    public static void main(String[] args) {
        SpringApplication.run(MainSentinel8401.class,args);
    }
}
