package com.atguigu.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloudalibaba.config.ApplicationContextConfig;
import com.atguigu.cloudalibaba.services.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/31 10:56
 */
@RestController
@Slf4j
public class CircleBreakerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    //测试一：只配置blockhandler，blockhandler属性用于处理控制台定义的各种降级限流规则，不对微服务的逻辑进行处理
    //注意：当不配置@Sentinel 时，会有默认的服务熔断，当配置@Sentinel 的blockhandler 时，控制台的资源名要指定为value的值
    @RequestMapping(value = "/consumer/testblockhandler/{id}")
    @SentinelResource(value = "testblockhandler",blockHandler = "testBlockhandler")
    public CommonResult<Payment> testblockhandler(@PathVariable("id")Long id){
        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException  非法参数异常。。。");
        }else if(result.getResult() == null){
            throw new NullPointerException("NullpointerExcetion   空指针异常。。。");
        }
        return result;
    }

    //测试二：只配置fallback,fallback属性用于处理逻辑内的错误，对该错误进行降级熔断处理，控制台的各种限流规则它不管
    @RequestMapping(value = "/consumer/testfallback/{id}")
    @SentinelResource(value = "testfallback",fallback = "testFallback")
    public CommonResult<Payment> testfallback(@PathVariable("id")Long id){
        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException  非法参数异常。。。");
        }else if(result.getResult() == null){
            throw new NullPointerException("NullpointerExcetion   空指针异常。。。");
        }
        return result;
    }

    //测试三：配置fallback和blockhandler，各司其事,各管各的
    @RequestMapping(value = "/consumer/testblockhandlerAndFallback/{id}")
    @SentinelResource(value = "testfallback",fallback = "testFallback",blockHandler = "testBlockhandler")
    public CommonResult<Payment> testblockhandlerAndFallback(@PathVariable("id")Long id){
        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException  非法参数异常。。。");
        }else if(result.getResult() == null){
            throw new NullPointerException("NullpointerExcetion   空指针异常。。。");
        }
        return result;
    }

    //*****************测试fallback属性start*****************//
    public CommonResult<Payment> testFallback(Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(400,"测试fallback属性，兜底异常fallback,exception内容  " + e.getMessage(),payment);
    }
    //*****************测试fallback属性end*******************//

    //*****************测试blockhandler属性start*******************//
    public CommonResult<Payment> testBlockhandler(Long id, BlockException e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(500,"测试blockhandler属性，异常blockhandler,exception内容  " + e.getMessage(),payment);
    }
    //*****************测试blockhandler属性end*******************//

    //*****************整合Openfeign****************************//
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> testOpenfeign(@PathVariable("id")Long id){
        return paymentService.testOpenfeign(id);
    }
}
