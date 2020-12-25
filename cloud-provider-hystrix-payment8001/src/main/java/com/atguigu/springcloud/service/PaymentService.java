package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

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

    //模拟卡顿
    public String paymentInfo_TimeOut(Integer id) {
//        int i = 10 / 0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "\t paymentInfo_TimeOut\t id:" + id + "\t 耗时：" + timeNumber;
    }

}
