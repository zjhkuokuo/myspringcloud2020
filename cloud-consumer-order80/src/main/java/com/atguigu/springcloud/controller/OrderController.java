package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myloadbalance.MyLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/12 11:20
 */
@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001";    //单机版
    //集群版，注意：只需填写加入Eureka Server的服务名（该服务名下可能有多个微服务提供者），同时在配置进容器中的RestTemplate()上加注解@LoadBalanced 即可实现负载均衡
    //此时的访问服务提供者的地址为：http://localhost/*  如：http://localhost/consumer/payment/serverport
    private static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    //用于自定义负载均衡
    @Resource
    private MyLoadBalance myLoadBalance;
    @Resource
    private DiscoveryClient discoveryClient;

    //因为是模拟客户端去请求8081支付模块，所有所有的请求都是get请求
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> createPayment(Payment payment){
        //写操作发送post请求
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        //读操作发送get请求
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/serverport")
    public String getPaymentLoadBalance(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance instance = myLoadBalance.getInstance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/serverport",String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
        return result;
    }
}
