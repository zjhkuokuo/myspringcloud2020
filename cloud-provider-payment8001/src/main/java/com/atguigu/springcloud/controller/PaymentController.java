package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/9 16:54
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //获取yml文件中的端口号
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("插入成功，共" + result + "行受影响。");
        if (result > 0 ){
            return new CommonResult(200,"插入数据成功！serverPort:" + serverPort,result);
        }else {
            return new CommonResult(404,"插入数据失败！",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询到的数据为：" + payment);
        if (payment != null){
            return new CommonResult(200,"查询已完成！serverPort:" + serverPort,payment);
        }else {
            return new CommonResult(404,"没有对应的记录，查询ID：" + id,null);
        }
    }

    //用于自定义的负载均衡
    @GetMapping("/payment/serverport")
    public String getServerPort(){
        return serverPort;
    }

    //测试超时时间
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
