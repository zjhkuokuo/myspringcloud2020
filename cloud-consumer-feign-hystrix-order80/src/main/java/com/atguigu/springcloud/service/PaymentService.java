package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  fallback属性指定继承了该接口的class类，该类中定义了对应方法的服务降级处理。
 *  该属性对应的方法执行的顺序高于controller层对应的方法上使用的@HystrixCommand接口的服务降级处理,原因是它在服务提供
 *  方出错时就已经进行服务降级处理了，还没到controller层，但是如果是客户端的controller层出现故障，那就会执行@HystrixCommadn
 *  的服务降级
 *
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/25 16:12
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
