package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *  1.@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod" 定义全局服务降级访问的方法，在没指定特
 *      定服务降级的方法而需要进行服务降级的方法上(如下的paymentInfoOk()方法)添加@HystrixCommand即可,已指定的按照就近原则，
 *      调用指定的降级方法即可。
 *  2.另一种通配降级方法见接口
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/25 16:07
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")  //配置访问超时的时间
})
public class OrderHystrixController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfoOk(@PathVariable("id") Integer id){
        int i = 10 / 0;     //在客户端调用提供者之前出现故障，执行@HystrixCommand的服务降级，而不是fallback
        try {
            TimeUnit.SECONDS.sleep(3);  //在客户端调用提供者之前出现故障，执行@HystrixCommand的服务降级，而不是fallback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentService.paymentInfoOk(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")  //配置访问超时的时间
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_TimeOut(id);
    }

    //指定的服务降级方法返回值和参数要和指定被服务降级(即加了@HystrixCommand注解)的方法一致
    public String paymentTimeOutFallbackMethod(Integer id){
        return "消费者client端调用提供者server端微服务报错，服务降级。";
    }

    //定义全部服务降级的方法
    public String payment_Global_FallbackMethod(){
        return "统一服务降级，访问繁忙，请稍后重试！";
    }
}
