package com.atguigu.cloudalibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/31 10:26
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long,Payment> map = new HashMap<>();
    static {
        map.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181"));
        map.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182"));
        map.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        Payment payment = map.get(id);
        CommonResult commonResult = new CommonResult(200,"from mysql ,serverPort: " + serverPort,payment);
        return  commonResult;
    }
}
