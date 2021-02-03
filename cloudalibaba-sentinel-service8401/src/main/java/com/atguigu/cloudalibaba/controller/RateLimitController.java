package com.atguigu.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloudalibaba.myhandler.CustomerBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/29 17:11
 */
@RestController
@Slf4j
public class RateLimitController {

    //结论：按url作为资源名的限流规则直接执行默认的熔断降级，而按照@Sentinel的value值作为资源名则可以返回配置
    //      了blockHandler对应的方法 ； 当又配置了URL又配置了value值两种规则，以URL的规则为先

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResource",blockHandler = "blockbandlerException")
    public CommonResult byResource(){
        return new CommonResult(200,"资源名按照@SentinelResource的value值限流测试ok",new Payment(2020L,"serial2020"));
    }
    public CommonResult blockbandlerException(BlockException e ){
        return new CommonResult(400,e.getClass().getCanonicalName()+"\t 服务不可用");
    }
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byurl(){
        return new CommonResult(200,"资源名按照url限流测试ok",new Payment(2020L,"serial2020"));
    }

    @GetMapping("/rateLimit/customerBlockhandler")
    @SentinelResource(value = "customerBlockhandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult customerBlockhandler(){
        return new CommonResult(200,"自定义限流ok",new Payment(2020L,"serial2021"));
    }

}
