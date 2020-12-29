package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 *  模拟服务降级
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/25 14:35
 */
@Service
public class PaymentService {

    //模拟正常访问的方法
    public String paymentInfo_OK(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "\t paymentInfo_Ok\t id:" + id;
    }

    //模拟卡顿，出现卡顿后使用@HystrixCommand指定对应的方法，实现服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int i = 10 / 0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "\t paymentInfo_TimeOut\t id:" + id + "\t 耗时：" + timeNumber;
    }

    //服务降级：当调用加了@HystrixCommand 注解的方法出现错误时（报错或超时，根据注解配置而定），转而调用这个方法返回给客户端
    //指定的服务降级方法返回值和参数要和指定被服务降级(即加了@HystrixCommand注解)的方法一致
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "\t 系统繁忙，请稍后再试。 \t id:" + id;
    }

    //*****************服务熔断开始*****************
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //如下时间范围内的请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")     //时间范围内请求次数的失败率，60%，达到就断路器开启
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0 ){
            throw  new RuntimeException("模拟异常，id不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t 调用成功，id：" + uuid;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "模拟服务熔断，当断路器开启时进行服务降级调用该方法。id：" + id;
    }
    //*****************服务熔断结束*****************

}
